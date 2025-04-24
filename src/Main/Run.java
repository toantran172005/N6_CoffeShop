package Main;


import javax.swing.UIManager;

import Frames.customerFrame;
import Frames.employeeFrame;
import Frames.loginFrame;

public class Run {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new employeeFrame(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
