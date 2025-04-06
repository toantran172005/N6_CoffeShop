package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Frames.loginFrame;
import Frames.registerFrame;

public class loginCtrl implements ActionListener {
	private loginFrame login;
	
	public loginCtrl(loginFrame login) {
		super();
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equalsIgnoreCase("Đăng nhập")) {
			
		} else if(str.equalsIgnoreCase("Đăng kí")) {
			this.login.dispose();
			new registerFrame();
		}
		
	}

}
