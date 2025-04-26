package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import DAO.orderDAO;
import Frames.employeeFrame;
import Frames.orderDetailFrame;

public class orderDetailCtrl implements ActionListener{
	private orderDetailFrame ordeFrame;
	private orderDAO ordDao;
	private employeeFrame emp;
	private InvoiceCtrl Invoice;
	public orderDetailCtrl(orderDetailFrame ordeFrame,employeeFrame emp) {
		super();
		this.emp=emp;
		this.ordeFrame=ordeFrame;
		this.ordDao = new orderDAO();
		this.Invoice = new InvoiceCtrl();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if(o.equals(ordeFrame.btnBack)) {
			this.emp.changeToOrder();
		}else if(o.equals(ordeFrame.btnCash)){
			int ID=ordeFrame.o.getOrderID();
			int IDemp=emp.employeeID;
			boolean check=ordDao.updateOrderState(ID,IDemp, "Dã xử lí");
			if(check) {
				JOptionPane.showMessageDialog(null, "Đã xác nhận");
			}
			else {
				JOptionPane.showMessageDialog(null, "Lỗi");
			}
		}else if(o.equals(ordeFrame.btnPrint)){
			Invoice.exportInvoiceToFile(ordeFrame.o, ordeFrame, emp);
		}
	}
}
