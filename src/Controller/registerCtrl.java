package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import DAO.CustomerDAOIMP;
import Frames.loginFrame;
import Frames.registerFrame;

public class registerCtrl implements ActionListener, WindowListener {
	private registerFrame register;
	private CustomerDAOIMP cusDAO;

	public registerCtrl(registerFrame register) {
		super();
		this.register = register;
		cusDAO = new CustomerDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equalsIgnoreCase("Đăng kí")) {
			String cusName = this.register.txt_cusName.getText().trim();
			String email = this.register.txt_email.getText().trim();
			String pwd = new String(this.register.txt_pwd.getPassword());
			String phone = this.register.txt_phone.getText().trim();
			String address = this.register.txt_address.getText().trim();

			int checkData = this.cusDAO.registerCustomer(cusName, email, pwd, phone, address);
		} else if(str.equalsIgnoreCase("Đăng nhập")) {
			this.register.dispose();
			new loginFrame();
		}
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		this.cusDAO.closeDatabase();
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
