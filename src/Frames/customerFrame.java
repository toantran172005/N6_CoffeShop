package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Controller.customerCtrl;
import DAO.CustomerDAOIMP;
import Models.Products;

public class customerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int customerID;
	public CustomerDAOIMP cusDAO;
	public JLabel lbCoffee;
	public JButton btnTimKiem;
	public JLabel lbMenu;
	public JLabel lbCart;
	public JButton btnTatCa;
	public JButton btnDrink;
	public JButton btnFood;
	public customerCtrl cusCtrl;
	public Map<JButton, JLabel> mapPlus;
	public Map<JButton, JLabel> mapMinus;
	public ArrayList<Products> listProduct;
	public JPanel pnlContent;
	public Map<JButton, Products> mapAddToCart;
	public Map<JButton, JLabel> mapQuantity;
	public JScrollPane scrollProduct;
	public RoundedTextField txtTimKiem;
	public JPanel pnlMenu;
	public boolean isMenuAppear = false;
	public JLabel lblInfo;
	public JLabel lblLogout;
	public Component lbN6;
	public JLabel lblLogin;
	public JLabel lblHome;

	public customerFrame(int customerID) {
		super("Nhóm 6 - CoffeeShop");
		this.customerID = customerID;
		this.cusDAO = new CustomerDAOIMP();
		this.cusCtrl = new customerCtrl(this);
		this.mapPlus = new HashMap<>();
		this.mapMinus = new HashMap<>();
		this.mapAddToCart = new HashMap<>();
		this.mapQuantity= new HashMap<>();
		this.listProduct = new ArrayList<>();
		this.setListProduct(cusDAO.getListProductFromDb());
		this.addWindowListener(cusCtrl);

		this.loadCusInfor();
	}

	public void loadCusInfor() {
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		Panel content
		JPanel pnlContent = this.loadProduct(listProduct);
		scrollProduct = new JScrollPane(pnlContent);
		scrollProduct.getVerticalScrollBar().setUnitIncrement(20);

//		Frame chính
		JPanel pnlEmpty = new JPanel(new BorderLayout());
		pnlEmpty.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnlEmpty.add(this.loadNavBar(), BorderLayout.NORTH); // Thêm thanh navbar
		pnlEmpty.add(scrollProduct, BorderLayout.CENTER); // Hiển thị sản phẩm
		pnlMenu = createMenu();
		pnlEmpty.add(pnlMenu, BorderLayout.EAST); // Thanh menu

		this.add(pnlEmpty, BorderLayout.CENTER);
		this.setVisible(true);

	}

	public JPanel loadNavBar() {
//		Thanh navbar
//		Phần west
		ImageIcon coffeeIcon = new ImageIcon(getClass().getResource("/Img/coffee.png"));
		Image coffeeImg = coffeeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbCoffee = new JLabel(new ImageIcon(coffeeImg));
		lbCoffee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbN6 = new JLabel("Nhóm 6 - Coffee shop");
		lbN6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbN6.setFont(new Font("Times New Roman", Font.BOLD, 30));
		Box bWest = Box.createHorizontalBox();
		bWest.add(lbCoffee);
		bWest.add(Box.createHorizontalStrut(20));
		bWest.add(lbN6);
//		Phần east
//		Tìm kiếm
		txtTimKiem = new RoundedTextField(25);
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

		lbCoffee.addMouseListener(cusCtrl);
		lbN6.addMouseListener(cusCtrl);
		btnTimKiem.addActionListener(cusCtrl);
		lbCart.addMouseListener(cusCtrl);
		lbMenu.addMouseListener(cusCtrl);

		JPanel pnlNavBar = new JPanel(new BorderLayout());
		pnlNavBar.add(bWest, BorderLayout.WEST);
		pnlNavBar.add(bEast, BorderLayout.EAST);
		JPanel pnlEmptyNavbar = new JPanel(new BorderLayout());
		pnlEmptyNavbar.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlEmptyNavbar.add(pnlNavBar);

		return pnlEmptyNavbar;
	}

	public JPanel loadProduct(ArrayList<Products> listProduct) {
		JPanel pnlGridBag = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);

		JPanel filterPanel = new JPanel(new FlowLayout());
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

		btnTatCa.addActionListener(cusCtrl);
		btnDrink.addActionListener(cusCtrl);
		btnFood.addActionListener(cusCtrl);

		pnlGridBag.add(filterPanel, gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 1;

		int gridx = 0, gridy = 1;

		for (int i = 0; i < listProduct.size(); i++) {
			Products p = listProduct.get(i);

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
			mapAddToCart.put(btnAddtoCart, p);
			mapQuantity.put(btnAddtoCart, quantity);
			btnPlus.addActionListener(cusCtrl);
			btnMinus.addActionListener(cusCtrl);
			btnAddtoCart.addActionListener(cusCtrl);

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
			item.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
					BorderFactory.createEmptyBorder(0, 0, 10, 0)));

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

		JPanel footerPanel = new JPanel(new GridLayout(1, 3, 0, 0));
		footerPanel.setPreferredSize(new Dimension(0, 120));

		int r = 221;
		int g = 220;
		int b = 215;
		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		Color backgroundColor = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		footerPanel.setBackground(backgroundColor);

//			Thông tin cửa hàng
		JPanel infoSection = new JPanel(new BorderLayout());
		infoSection.setBackground(backgroundColor);
		JLabel infoTitle = new JLabel("Nhóm 6 - Coffee Shop", SwingConstants.CENTER);
		infoTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JLabel infoContent = new JLabel("<html>Đem đến trải nghiệm tốt nhất<br>"
				+ "Thưởng thức hương vị cà phê đậm đà<br>" + "Hãy đến trải nghiệm ngay hôm nay!</html>",
				SwingConstants.CENTER);
		infoContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		infoSection.add(infoTitle, BorderLayout.NORTH);
		infoSection.add(infoContent, BorderLayout.CENTER);
		footerPanel.add(infoSection);

//			Liên hệ
		JPanel contactSection = new JPanel(new BorderLayout());
		contactSection.setBackground(backgroundColor);
		JLabel contactTitle = new JLabel("Liên Hệ", SwingConstants.CENTER);
		contactTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JLabel contactContent = new JLabel("<html>Địa chỉ: Trường Đại Học Công Nghiệp TP.HCM<br>"
				+ "Email: Nhom6CoffeeShop@gmail.com<br>" + "Số Liên Lạc: (+84) 123-456-789</html>",
				SwingConstants.CENTER);
		contactContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contactSection.add(contactTitle, BorderLayout.NORTH);
		contactSection.add(contactContent, BorderLayout.CENTER);
		footerPanel.add(contactSection);

//			Thành viên
		JPanel memberSection = new JPanel(new BorderLayout());
		memberSection.setBackground(backgroundColor);
		JLabel memberTitle = new JLabel("Thành Viên", SwingConstants.CENTER);
		memberTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JLabel memberContent = new JLabel("<html>Trần Thanh Toàn - 23722181<br>Nguyễn Thành Tài - 23715171</html>",
				SwingConstants.CENTER);
		memberContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		memberSection.add(memberTitle, BorderLayout.NORTH);
		memberSection.add(memberContent, BorderLayout.CENTER);
		footerPanel.add(memberSection);

		gbc.gridx = 0;
		gbc.gridy = gridy;
		gbc.gridwidth = 3;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		JPanel filler = new JPanel();
		filler.setOpaque(false);
		pnlGridBag.add(filler, gbc);

		gridy++;

		gbc.gridx = 0;
		gbc.gridy = gridy;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 0);
		pnlGridBag.add(footerPanel, gbc);

		return pnlGridBag;
	}

	public void updateProductPanel(ArrayList<Products> searchList) {
		pnlContent = loadProduct(searchList);
		scrollProduct.setViewportView(pnlContent);
		scrollProduct.revalidate();
		scrollProduct.repaint();
	}

	private JPanel createMenu() {
		pnlMenu = new JPanel();
		this.pnlMenu.setLayout(new BoxLayout(this.pnlMenu, BoxLayout.Y_AXIS));
		this.pnlMenu.setPreferredSize(new Dimension(200, getHeight()));

//	     Các mục menu
		lblHome = new JLabel("Trang chủ");
		lblInfo = new JLabel("Thông tin cá nhân");
		lblLogout = new JLabel("Đăng xuất");
		lblLogin = new JLabel("Đăng nhập");

		for (JLabel label : new JLabel[] { lblHome, lblInfo, lblLogout, lblLogin }) {
			label.setFont(new Font("Times New Roman", Font.BOLD, 18));
			label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.addMouseListener(cusCtrl);
			this.pnlMenu.add(label);
		}

		this.pnlMenu.setVisible(false);
		return pnlMenu;
	}

	public void changeToInfor() {
		inforFrame inf = new inforFrame(this.customerID, this);
		pnlContent = inf.getInfPanel();
		scrollProduct.setViewportView(pnlContent);
		scrollProduct.revalidate();
		scrollProduct.repaint();
	}
	
	public void changToCart() {
		cartFrame cart = new cartFrame(this.customerID, this);
		pnlContent = cart.getCartPanel();
		scrollProduct.setViewportView(pnlContent);
		scrollProduct.revalidate();
		scrollProduct.repaint();
	}


	public ArrayList<Products> getListProduct() {
		return listProduct;
	}

	public void setListProduct(ArrayList<Products> listProduct) {
		this.listProduct = listProduct;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public class RoundedTextField extends JTextField {
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

	public class ImagePanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
			this.setPreferredSize(new Dimension(350, 250));
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}

}
