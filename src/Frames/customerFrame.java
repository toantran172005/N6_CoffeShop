package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Controller.customerCtrl;

public class customerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerID;
	public JLabel lbCoffee;
	public JButton btnTimKiem;
	public JLabel lbMenu;

	public customerFrame(int CustomerID) {
		super("Nhóm 6 - CoffeeShop");
		this.customerID = CustomerID;
		if(customerID != 0)
			loadCusInfor();
		else loadCusNoInfor();
	}


	public void loadCusInfor() {
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		customerCtrl cusCtrl = new customerCtrl(this);

//		Thanh navbar
//		Phần west
		ImageIcon coffeeIcon = new ImageIcon(getClass().getResource("/Img/coffee.png"));
		Image coffeeImg = coffeeIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		lbCoffee = new JLabel(new ImageIcon(coffeeImg));
		lbCoffee.addMouseListener(cusCtrl);
		lbCoffee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JLabel lbN6 = new JLabel("Nhóm 6 - Coffee shop");
		lbN6.setFont(new Font("Times New Roman", Font.BOLD, 30));
		Box bWest = Box.createHorizontalBox();
		bWest.add(lbCoffee);
		bWest.add(Box.createHorizontalStrut(20));
		bWest.add(lbN6);
//		Phần east
		RoundedTextField txtTimKiem = new RoundedTextField(25);
		txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ImageIcon searchIcon = new ImageIcon(getClass().getResource("/Img/search.png"));
		Image searchImg = searchIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btnTimKiem = new JButton(new ImageIcon(searchImg));
		btnTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ImageIcon menuIcon = new ImageIcon(getClass().getResource("/Img/menu.png"));
		Image menuImg = menuIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbMenu = new JLabel(new ImageIcon(menuImg));
		lbMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		Box bEast = Box.createHorizontalBox();
		bEast.add(txtTimKiem);
		bEast.add(Box.createHorizontalStrut(10));
		bEast.add(btnTimKiem);
		bEast.add(Box.createHorizontalStrut(10));
		bEast.add(lbMenu);

		JPanel pnlNavBar = new JPanel(new BorderLayout());
		pnlNavBar.add(bWest, BorderLayout.WEST);
		pnlNavBar.add(bEast, BorderLayout.EAST);

		JPanel pnlEmpty = new JPanel(new BorderLayout());
		pnlEmpty.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		pnlEmpty.add(pnlNavBar, BorderLayout.NORTH); // Thêm thanh navbar
		this.add(pnlEmpty, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void loadCusNoInfor() {
		// TODO Auto-generated method stub
		
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	class RoundedTextField extends JTextField {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public RoundedTextField(int size) {
			super(size);
			setOpaque(false);
			setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
			super.paintComponent(g);
			g2.dispose();
		}

		@Override
		protected void paintBorder(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.GRAY);
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 35, 35);
		}
	}

}
