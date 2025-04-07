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
		if (str.equalsIgnoreCase("Đăng kí")) {
			String cusName = this.register.txt_cusName.getText().trim();
			String email = this.register.txt_email.getText().trim();
			String pwd = new String(this.register.txt_pwd.getPassword());
			String phone = this.register.txt_phone.getText().trim();
			String address = this.register.txt_address.getText().trim();

			int checkData = this.cusDAO.registerCustomer(cusName, email, pwd, phone, address);
			if (checkData == 0)
				JOptionPane.showMessageDialog(register, "Đăng kí thành công!");
			else if (checkData == 1) {
				JOptionPane.showMessageDialog(register, "Vui lòng nhập đúng Họ và tên!", "Sai họ và tên",
						JOptionPane.ERROR_MESSAGE);
				this.register.txt_cusName.requestFocusInWindow();
				this.register.txt_cusName.selectAll();
			} else if (checkData == 2) {
				JOptionPane.showMessageDialog(register, "Vui lòng nhập đúng email!", "Sai email",
						JOptionPane.ERROR_MESSAGE);
				this.register.txt_email.requestFocusInWindow();
				this.register.txt_email.selectAll();
			} else if (checkData == 3) {
				JOptionPane.showMessageDialog(register, "Vui lòng nhập đúng mật khẩu!", "Sai mật khẩu",
						JOptionPane.ERROR_MESSAGE);
				this.register.txt_pwd.requestFocusInWindow();
				this.register.txt_pwd.selectAll();
			} else if (checkData == 4) {
				JOptionPane.showMessageDialog(register, "Vui lòng nhập đúng số liên lạc!", "Sai số liên lạc",
						JOptionPane.ERROR_MESSAGE);
				this.register.txt_phone.requestFocusInWindow();
				this.register.txt_phone.selectAll();
			} else if (checkData == 5) {
				JOptionPane.showMessageDialog(register, "Vui lòng nhập đúng địa chỉ!", "Sai địa chỉ",
						JOptionPane.ERROR_MESSAGE);
				this.register.txt_address.requestFocusInWindow();
				this.register.txt_address.selectAll();
			} else if (checkData == -1)
				JOptionPane.showMessageDialog(register, "Email đã tồn tại!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			this.register.txt_email.requestFocusInWindow();
			this.register.txt_email.selectAll();
		} else if (str.equalsIgnoreCase("Đăng nhập")) {
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
