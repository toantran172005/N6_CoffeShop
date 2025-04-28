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
	private int a;

	public orderDetailCtrl(orderDetailFrame ordeFrame, employeeFrame emp, int a) {
		super();
		this.emp = emp;
		this.ordeFrame = ordeFrame;
		this.ordDao = new orderDAOIMP();
		this.Invoice = new InvoiceCtrl();
		this.a = a;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JButton) {

			JButton btn = (JButton) obj;
//			Quay lại
			if (btn.equals(ordeFrame.btnBack)) {
				if(a!=0) {
					SwingUtilities.invokeLater(() -> {
						this.emp.changeToStatistic();
		            });
					
				} else {
					SwingUtilities.invokeLater(() -> {
						this.emp.changeToOrder();
		            });
				}
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
			} else if (btn.equals(ordeFrame.btnDeleteInvoice)) {
	            int confirm = JOptionPane.showConfirmDialog(this.emp, "Bạn có chắc chắn muốn xóa hóa đơn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
	            if (confirm == JOptionPane.YES_OPTION) {
	                boolean checkDelete = this.ordDao.deleteOrder(this.ordeFrame.o.getOrderID());
	                if (checkDelete) {
	                    JOptionPane.showMessageDialog(this.emp, "Hóa đơn đã được xóa");
	                    SwingUtilities.invokeLater(() -> {
	                        this.emp.changeToOrder();
	                    });
	                } else {
	                    JOptionPane.showMessageDialog(this.emp, "Lỗi khi xóa hóa đơn");
	                }
	            }
	        }

		}

	}
	
	public boolean updateOrder() {
		int ID = ordeFrame.getO().getOrderID();
		int IDemp = emp.getEmployeeID();
		return ordDao.updateOrderState(ID, IDemp, "Đã xử lí");
	}
}
