package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DAO.CustomerDAOIMP;
import Frames.customerFrame;
import Frames.employeeFrame;
import Frames.loginFrame;
import Frames.registerFrame;

public class loginCtrl implements ActionListener, WindowListener, MouseListener {
	private loginFrame login;
	private CustomerDAOIMP cusDAO;

	public loginCtrl(loginFrame login) {
		super();
		this.login = login;
		cusDAO = new CustomerDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equalsIgnoreCase("Đăng nhập")) {
			String email = this.login.txt_email.getText().trim();
			String pwd = new String(this.login.txt_pwd.getPassword());
			int checkLogin = cusDAO.loginCustomer(email, pwd);
			if (checkLogin > 0) { // trả về > 0 => Customer
				JOptionPane.showMessageDialog(login, "Đăng nhập thành công!");
				this.login.dispose();
				new customerFrame(checkLogin);
			} else if (checkLogin == 0) { // trả về = 0 => Employee
				JOptionPane.showMessageDialog(login, "Đăng nhập thành công!");
				this.login.dispose();
				new employeeFrame(Integer.valueOf(pwd));
			} else if (checkLogin == -1) { // trả về -1, sai Email
				JOptionPane.showMessageDialog(login, "Vui lòng nhập đúng email!", "Sai email",
						JOptionPane.ERROR_MESSAGE);
				this.login.txt_email.requestFocusInWindow();
				this.login.txt_email.selectAll();
			} else if (checkLogin == -404) { // trả về -404 => sai mật khẩu
				JOptionPane.showMessageDialog(login, "Vui lòng nhập đúng mật khẩu!", "Sai mật khẩu",
						JOptionPane.ERROR_MESSAGE);
				this.login.txt_pwd.requestFocusInWindow();
				this.login.txt_pwd.selectAll();
			}
		} else if (str.equalsIgnoreCase("Đăng kí")) {
			this.login.dispose();
			new registerFrame();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.cusDAO.closeDatabase();

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

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel source = (JLabel) e.getSource();
		if (source == this.login.lb_forgotPassword) {
			String input = JOptionPane.showInputDialog("Nhập email:");
			if (input != null) {
				String pwd = this.cusDAO.forgotPassword(input);
				if (pwd != null)
					JOptionPane.showMessageDialog(login, "Mật khẩu của bạn là : " + pwd);
				else
					JOptionPane.showMessageDialog(login, "Không tìm thấy email!");
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
