package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Frames.loginFrame;
import Frames.registerFrame;

public class registerCtrl implements ActionListener {
	private loginFrame login;
	private registerFrame register;

	public registerCtrl(registerFrame register) {
		super();
		this.register = register;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equalsIgnoreCase("Đăng kí")) {
			
		} else if(str.equalsIgnoreCase("Đăng nhập")) {
			this.register.dispose();
			new loginFrame();
		}
		
	}

	
}
