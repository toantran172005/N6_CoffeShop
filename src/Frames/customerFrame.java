package Frames;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class customerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public customerFrame() {
		super("Nhóm 6 - CoffeeShop");
		this.setSize(1300,1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new customerFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
