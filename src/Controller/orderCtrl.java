package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DAO.CustomerDAOIMP;
import Frames.customerFrame;
import Frames.orderFrame;
import Models.CartItems;
import Models.Customers;
import Models.Employees;
import Models.Orders;

public class orderCtrl implements ActionListener {
	public CustomerDAOIMP cusDAO;
	public customerFrame cusFrame;
	public orderFrame ordFrame;

	public orderCtrl(orderFrame ordFrame, customerFrame cusFrame) {
		this.ordFrame = ordFrame;
		this.cusFrame = cusFrame;
		this.cusDAO = new CustomerDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JButton) {
			JButton btn = (JButton) obj;
			if (btn == this.ordFrame.btnBack) {
				SwingUtilities.invokeLater(() -> {
					this.cusFrame.changToCart();
				});

			} else if (btn == this.ordFrame.btnTransfer) {
				this.cusFrame.changToQRPay(this.ordFrame.getList());
				this.createOrderForCus();
			} else if (btn == this.ordFrame.btnCash) {
				JOptionPane.showMessageDialog(this.cusFrame,
						"Hóa đơn đã được in, vui lòng kiểm tra trước khi thanh toán");
				this.createOrderForCus();
				SwingUtilities.invokeLater(() -> {
					this.cusFrame.changToCart();
				});
			}

		}

	}

	public void createOrderForCus() {
		Customers cus = new Customers(this.cusFrame.getCustomerID());
		Employees emp = new Employees(0);
		double totalPrice = this.ordFrame.orderTotal;

		Orders order = new Orders(cus, emp, totalPrice);

		this.cusDAO.createOrder(order, this.ordFrame.getList());
		this.cusDAO.clearCart(this.cusFrame.getCustomerID(), this.ordFrame.getList());

	}

	public ArrayList<CartItems> getListItems() {
		ArrayList<CartItems> list = cusDAO.getCartItemsByCustomerID(this.cusFrame.getCustomerID());
		return list;
	}

}
