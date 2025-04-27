package Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.EmployeeDAOIMP;
import DAO.orderDAOIMP;
import Frames.employeeFrame;
import Frames.orderDetailFrame;
import Models.Employees;
import Models.OrderDetails;
import Models.Orders;

public class InvoiceCtrl {

	public orderDAOIMP ordDAO;
	private List<OrderDetails> list;

	public void exportInvoiceToFile(Orders order, orderDetailFrame orDe, employeeFrame emp) {
		this.ordDAO = new orderDAOIMP();
		this.list = ordDAO.getOrderDetailsByOrderID(order.getOrderID());
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("HoaDon_" + order.getOrderID() + ".txt"));
			writer.write("\t   Nhóm 6 - Coffee Shop\n");
			writer.write("\t   OrderID:" + order.getOrderID() + "\n");
			writer.write("04 Nguyễn Văn Bảo, phường 4, Gò Vấp\n");
			EmployeeDAOIMP empDAO = new EmployeeDAOIMP();
			int Name = emp.employeeID;

			Employees empl = empDAO.getByID(Name);
			String empName = empl.getEmployeeName();
			String date = order.getOrderDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			writer.write("Date: " + date + "\n");
			writer.write("EmployeeName: " + empName + "\n");
			writer.write("Sản phẩm              Số lượng           Đơn giá\n");
			writer.write("-------------------------------------------------\n");

			int total = 0;
			for (OrderDetails detail : list) {
				String itemName = detail.getProductID().getProductName();
				int quantity = detail.getQuantity();
				int price = (int) detail.getProductID().getPrice();
				int amount = price * quantity;
				total += amount;

				writer.write(String.format("%-21s %-18d %,d₫\n", itemName, quantity, amount));
			}

			writer.write("-------------------------------------------------\n");
			writer.write(String.format("TOTAL(+VAT):                             %,d₫\n", total));
			writer.write("-------------------------------------------------\n");
			writer.write("-------Xin cảm ơn vì đã sử dụng dịch vụ----------\n");

			writer.close();

			JOptionPane.showMessageDialog(emp, "Hóa đơn đã được in ra file");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(emp, "Lỗi khi ghi file hóa đơn!");
		}
	}

}
