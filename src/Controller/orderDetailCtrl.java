package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DAO.orderDAOIMP;
import Frames.employeeFrame;
import Frames.orderDetailFrame;

public class orderDetailCtrl implements ActionListener {
	private orderDetailFrame ordeFrame;
	private orderDAOIMP ordDao;
	private employeeFrame emp;
	private InvoiceCtrl Invoice;

	public orderDetailCtrl(orderDetailFrame ordeFrame, employeeFrame emp) {
		super();
		this.emp = emp;
		this.ordeFrame = ordeFrame;
		this.ordDao = new orderDAOIMP();
		this.Invoice = new InvoiceCtrl();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JButton) {

			JButton btn = (JButton) obj;
//			Quay lại
			if (btn.equals(ordeFrame.btnBack)) {
				this.emp.changeToOrder();
//			Xác nhận thanh toán
			} else if (btn.equals(ordeFrame.btnAccept)) {
				boolean check = this.updateOrder();
				if (check) {
					JOptionPane.showMessageDialog(this.emp, "Đã xác nhận");
					SwingUtilities.invokeLater(() -> {
						this.emp.changeToOrder();
		            });
					
				} else {
					JOptionPane.showMessageDialog(this.emp, "Lỗi");
				}
//			In hóa đơn
			} else if (btn.equals(ordeFrame.btnPrint)) {
				Invoice.exportInvoiceToFile(ordeFrame.getO(), ordeFrame, emp);
				this.updateOrder();
				SwingUtilities.invokeLater(() -> {
					this.emp.changeToOrder();
	            });
			}

		}

	}
	
	public boolean updateOrder() {
		int ID = ordeFrame.getO().getOrderID();
		int IDemp = emp.getEmployeeID();
		return ordDao.updateOrderState(ID, IDemp, "Đã xử lí");
	}
}
