package Main;


import javax.swing.UIManager;

import Frames.customerFrame;

public class Run {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new customerFrame(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
