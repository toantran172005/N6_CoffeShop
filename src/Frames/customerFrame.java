package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import Controller.customerCtrl;
import Database.DatabaseConnection;
import Models.Products;

public class customerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerID;
	public JLabel lbCoffee;
	public JButton btnTimKiem;
	public JLabel lbMenu;
	public JLabel lbCart;
	public JButton btnTatCa;
	public JButton btnDrink;
	public JButton btnFood;
	public customerCtrl cusCtrl = new customerCtrl(this);
	public Map<JButton, JLabel> mapPlus = new HashMap<>();
	public Map<JButton, JLabel> mapMinus = new HashMap<>();
//	Map<JButton, Products> mapAddToCart = new HashMap<>(); // thêm vào giỏ hàng
//	Map<Products, Integer> productQuantities = new HashMap<>(); // Chứa product và số lượng mua

	public customerFrame(int CustomerID) {
		super("Nhóm 6 - CoffeeShop");
		this.customerID = CustomerID;
		if (customerID != 0)
			loadCusInfor();
		else
			loadCusNoInfor();
	}

	public void loadCusInfor() {
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		Thanh navbar
//		Phần west
		ImageIcon coffeeIcon = new ImageIcon(getClass().getResource("/Img/coffee.png"));
		Image coffeeImg = coffeeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
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
//		Tìm kiếm
		RoundedTextField txtTimKiem = new RoundedTextField(25);
		txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ImageIcon searchIcon = new ImageIcon(getClass().getResource("/Img/search.png"));
		Image searchImg = searchIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btnTimKiem = new JButton(new ImageIcon(searchImg));
		btnTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		Menu
		ImageIcon menuIcon = new ImageIcon(getClass().getResource("/Img/menu.png"));
		Image menuImg = menuIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbMenu = new JLabel(new ImageIcon(menuImg));
		lbMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		Giỏ hàng
		ImageIcon carrIcon = new ImageIcon(getClass().getResource("/Img/cart.png"));
		Image cartImg = carrIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbCart = new JLabel(new ImageIcon(cartImg));
		lbCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		Box bEast = Box.createHorizontalBox();
		bEast.add(txtTimKiem);
		bEast.add(Box.createHorizontalStrut(10));
		bEast.add(btnTimKiem);
		bEast.add(Box.createHorizontalStrut(10));
		bEast.add(lbCart);
		bEast.add(Box.createHorizontalStrut(10));
		bEast.add(lbMenu);

		JPanel pnlNavBar = new JPanel(new BorderLayout());
		pnlNavBar.add(bWest, BorderLayout.WEST);
		pnlNavBar.add(bEast, BorderLayout.EAST);
		JPanel pnlEmptyNavbar = new JPanel(new BorderLayout());
		pnlEmptyNavbar.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlEmptyNavbar.add(pnlNavBar);

//		Panel content
		JPanel pnlContent = this.loadProduct();
		JScrollPane scrollProduct = new JScrollPane(pnlContent);

//		Frame chính
		JPanel pnlEmpty = new JPanel(new BorderLayout());
		pnlEmpty.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnlEmpty.add(pnlEmptyNavbar, BorderLayout.NORTH); // Thêm thanh navbar
		pnlEmpty.add(scrollProduct, BorderLayout.CENTER); // Hiển thị sản phẩm
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

	public JPanel loadProduct() {
		ArrayList<Products> list = new ArrayList<>();
		JPanel pnlGridBag = new JPanel(new GridBagLayout());

		try {
			Connection con = DatabaseConnection.getConnection();
			String sql = "SELECT TOP 9 ProductID, ProductName, ProductType, Price, Quantity, Description, Size, ProductIMG FROM Products";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				Products pro = new Products();
				pro.setProductID(rs.getInt(1));
				pro.setProductName(rs.getString(2));
				pro.setProductType(rs.getString(3));
				pro.setPrice(rs.getBigDecimal(4).doubleValue());
				pro.setQuantity(rs.getInt(5));
				pro.setDescription(rs.getString(6));
				pro.setSize(rs.getString(7));
				pro.setProductImg(rs.getString(8));
				list.add(pro);
			}
			sta.close();
			rs.close();
			DatabaseConnection.closeConnection(con);

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 3; 
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(10, 10, 10, 10);

			JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			btnTatCa = new JButton("Tất cả");
			btnDrink = new JButton("Nước uống");
			btnFood = new JButton("Đồ ăn");
			btnTatCa.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btnDrink.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btnFood.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btnTatCa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnDrink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnFood.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			filterPanel.add(btnTatCa);
			filterPanel.add(btnDrink);
			filterPanel.add(btnFood);

			pnlGridBag.add(filterPanel, gbc);

			// Reset gridwidth và gridy cho sản phẩm
			gbc.gridwidth = 1;
			gbc.gridy = 1;

			int gridx = 0, gridy = 1;

			for (int i = 0; i < list.size(); i++) {
				Products p = list.get(i);

				JLabel proTitle = new JLabel(p.getProductName());
				proTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
				JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pnlTitle.add(proTitle);

				Font btnFont = new Font("Times New Roman", Font.BOLD, 18);
				JButton btnPlus = new JButton("+");
				btnPlus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnPlus.setFont(btnFont);
				JButton btnMinus = new JButton("-");
				btnMinus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnMinus.setFont(btnFont);
				JLabel quantity = new JLabel("1");
				quantity.setFont(btnFont);
				JButton btnAddtoCart = new JButton("Thêm vào giỏ hàng");
				btnAddtoCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnAddtoCart.setFont(btnFont);

				mapPlus.put(btnPlus, quantity);
				mapMinus.put(btnMinus, quantity);
				btnPlus.addActionListener(cusCtrl);
				btnMinus.addActionListener(cusCtrl);

				Box box = Box.createHorizontalBox();
				box.add(btnMinus);
				box.add(Box.createHorizontalStrut(5));
				box.add(quantity);
				box.add(Box.createHorizontalStrut(5));
				box.add(btnPlus);
				box.add(Box.createHorizontalStrut(20));
				box.add(btnAddtoCart);

				Box south = Box.createVerticalBox();
				south.add(pnlTitle);
				south.add(Box.createVerticalStrut(10));
				south.add(box);

				ImageIcon itemIcon = new ImageIcon(getClass().getResource(p.getProductImg()));
				Image itemImg = itemIcon.getImage();
				ImagePanel imagePanel = new ImagePanel(itemImg);

				JPanel item = new JPanel(new BorderLayout());
				item.add(imagePanel, BorderLayout.CENTER);
				item.add(south, BorderLayout.SOUTH);
				item.setPreferredSize(new Dimension(330, 250));
				item.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
					BorderFactory.createEmptyBorder(0, 0, 10, 0)
				));

				gbc.gridx = gridx;
				gbc.gridy = gridy;
				gbc.weightx = 1.0;
				gbc.weighty = 1.0;
				gbc.fill = GridBagConstraints.BOTH;
				gbc.insets = new Insets(10, 10, 10, 10);
				pnlGridBag.add(item, gbc);

				gridx++;
				if (gridx == 3) {
					gridx = 0;
					gridy++;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pnlGridBag;
	}


	public class ImagePanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
			this.setPreferredSize(new Dimension(350, 250)); // size ban đầu
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Vẽ ảnh scale theo kích thước hiện tại của panel
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}

}
