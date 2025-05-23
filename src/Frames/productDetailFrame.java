package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.proDetailCtrl;
import Models.Products;

public class productDetailFrame {
	public JLabel lbCoffee;
	public JButton btnTimKiem;
	public JLabel lbMenu;
	public JLabel lbCart;
	public JPanel pnlMain;
	public Products products;
	public JLabel lbN6;
	public proDetailCtrl proCtrl;
	public JPanel pnlMenu;
	public JLabel lblHome;
	public JLabel lblLogout;
	public JLabel lblInfo;
	public JLabel lblLogin;
	public boolean isMenuAppear = false;
	public int employeeID;
	public JPanel pnlTitle;
	public JLabel lblEdit;
	public JLabel lblname;
	public JButton btnSave;
	public JButton btnEdit;
	public JTextField txtName;
	public JTextField txtPrice;
	public JTextField txtDesc;
	public JComboBox<String> cmbSize;
	public JTextField txtQuantity;
	public JButton btnDelete;
	public employeeFrame empFrame;
	public JButton btnPlus;
	public JButton btnMinus;
	public JButton btnAddToCart;
	public JLabel lblQuantity;
	public Products proCus;
	public JButton btnBack;

	public Products getProCus() {
		return proCus;
	}

	public void setProCus(Products proCus) {
		this.proCus = proCus;
	}

	public productDetailFrame(Products product, int employeeID, employeeFrame empFrame) {
		this.products = product;
		this.employeeID = employeeID;
		this.empFrame = empFrame;
		this.proCtrl = new proDetailCtrl(this, product, empFrame);
	}

	public productDetailFrame(customerFrame cusFrame) {
		this.proCtrl = new proDetailCtrl(this, cusFrame);
	}

	public JPanel LoadProDuct(Products product) {
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		pnlMain.setForeground(Color.WHITE);

//		 NOUTH: Tiêu đề
		pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblEdit = new JLabel("Chỉnh sửa sản phẩm");
		lblEdit.setFont(new Font("Times New Roman", Font.BOLD, 40));
		pnlTitle.add(lblEdit);
		pnlMain.add(pnlTitle, BorderLayout.NORTH);

//		 LEFT PANEL: Ảnh + Form chỉnh sửa
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

//		 Card vuông hiển thị ảnh và tên
		JPanel squareCard = new JPanel();
		squareCard.setLayout(new BoxLayout(squareCard, BoxLayout.Y_AXIS));
		squareCard.setPreferredSize(new Dimension(350, 300));
		squareCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

//		 Ảnh
		ImageIcon imgIcon = new ImageIcon(getClass().getResource(product.getProductImg()));
		Image img = imgIcon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Canh giữa ảnh

//		 Tên sản phẩm
		lblname = new JLabel(product.getProductName());
		lblname.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblname.setAlignmentX(Component.CENTER_ALIGNMENT); // Canh giữa chữ
		lblname.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Cách ảnh 5px

//		 Thêm vào card
		squareCard.add(imgLabel);
		squareCard.add(lblname);
		Box formBox = Box.createVerticalBox();
		formBox.add(Box.createVerticalStrut(20));
		leftPanel.add(Box.createVerticalStrut(10));
		leftPanel.add(squareCard);
		leftPanel.add(Box.createVerticalStrut(20));
		leftPanel.add(formBox);

//		 RIGHT PANEL: Để trống hoặc thêm sau
		JPanel rightPanel = new JPanel();
		Font fo = new Font("Times New Roman", Font.BOLD, 25);
		Box nameBox = Box.createHorizontalBox();
		JLabel lblpName;
		nameBox.add(lblpName = new JLabel("Tên sản phẩm: "));
		lblpName.setFont(fo);
		txtName = new JTextField(product.getProductName(), 20);
		txtName.setFont(fo);
		txtName.setEnabled(false); // Ban đầu không cho phép chỉnh sửa
		nameBox.add(txtName);
		formBox.add(nameBox);
		formBox.add(Box.createVerticalStrut(20));

		// Giá
		Box priceBox = Box.createHorizontalBox();
		JLabel lblpPrice;
		priceBox.add(lblpPrice = new JLabel("Giá (VNĐ): "));
		lblpPrice.setFont(fo);

		// Format giá
		DecimalFormat formatter = new DecimalFormat("#,###");
		String formattedPrice = formatter.format(product.getPrice()) + "đ";

		txtPrice = new JTextField(formattedPrice, 20);
		txtPrice.setFont(fo);
		txtPrice.setEnabled(false);
		priceBox.add(txtPrice);
		formBox.add(priceBox);
		formBox.add(Box.createVerticalStrut(20)); 

//		 Mô tả
		Box descBox = Box.createHorizontalBox();
		JLabel lblpDep;
		descBox.add(lblpDep = new JLabel("Mô tả: "));
		lblpDep.setFont(fo);
		txtDesc = new JTextField(product.getDescription(), 20);
		txtDesc.setFont(fo);
		txtDesc.setEnabled(false);
		descBox.add(txtDesc);
		formBox.add(descBox);
		formBox.add(Box.createVerticalStrut(20));

//		 Size (ComboBox)
		Box sizeBox = Box.createHorizontalBox();
		JLabel lblpSize;
		sizeBox.add(lblpSize = new JLabel("Size: "));
		lblpSize.setFont(fo);
		String[] sizes;
		if (product.getProductTypeID().getProductTypeID() == 1) {
			sizes = new String[] { "Nhỏ", "Vừa", "Lớn" };
		} else {
			sizes = new String[] { "M", "L", "XL" };
		}
		cmbSize = new JComboBox<>(sizes);
		cmbSize.setFont(fo);
		cmbSize.setSelectedItem(product.getSize());
		cmbSize.setEnabled(false); // Ban đầu không cho phép chỉnh sửa
		sizeBox.add(cmbSize);
		formBox.add(sizeBox);
		formBox.add(Box.createVerticalStrut(20));

//		 Số lượng tồn
		Box quantityBox = Box.createHorizontalBox();
		JLabel lblpQuan;
		quantityBox.add(lblpQuan = new JLabel("Số lượng tồn: "));
		lblpQuan.setFont(fo);
		txtQuantity = new JTextField(String.valueOf(product.getQuantity()), 20);
		txtQuantity.setFont(fo);
		txtQuantity.setEnabled(false);
		quantityBox.add(txtQuantity);
		formBox.add(quantityBox);
		formBox.add(Box.createVerticalStrut(20));

		lblpPrice.setPreferredSize(lblpName.getPreferredSize());
		lblpDep.setPreferredSize(lblpName.getPreferredSize());
		lblpSize.setPreferredSize(lblpName.getPreferredSize());
		lblpQuan.setPreferredSize(lblpName.getPreferredSize());

		rightPanel.add(formBox);

		JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		btnSave = new JButton("Lưu");
		btnSave.setPreferredSize(new Dimension(150, 40));

		btnSave.setForeground(Color.WHITE);
		btnSave.setBorderPainted(false);
		btnSave.setEnabled(false);

		btnEdit = new JButton("Sửa");
		btnEdit.setPreferredSize(new Dimension(150, 40));
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBorderPainted(false);

		btnDelete = new JButton("Xóa");
		btnDelete.setPreferredSize(new Dimension(150, 40));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBorderPainted(false);

		btnBack = new JButton("Quay lại");
		btnBack.setPreferredSize(new Dimension(150, 40));
		btnBack.setBorderPainted(false);

		btnSave.setBackground(new Color(102, 187, 106)); // Xanh lá nhạt
		btnEdit.setBackground(new Color(79, 195, 247)); // Xanh dương nhạt
		btnDelete.setBackground(new Color(239, 83, 80)); // Đỏ nhạt
		btnBack.setBackground(new Color(176, 190, 197)); // Xám nhạt

		formBox.add(footer);
		footer.add(btnBack);
		footer.add(btnEdit);
		footer.add(btnSave);
		footer.add(btnDelete);

		for (JButton btn : new JButton[] { btnBack, btnEdit, btnSave, btnDelete }) {
			btn.addActionListener(proCtrl);
			btn.setFont(new Font("Times New Roman", Font.BOLD, 25));
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
//		 SPLIT PANE chia trái-phải
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setDividerLocation(400); // Khoảng cách giữa hai phần
		splitPane.setEnabled(false);
		splitPane.setDividerSize(0);

		pnlMain.add(splitPane, BorderLayout.CENTER);

//		 Tạo footerPanel
		JPanel footerPanel = new JPanel(new GridLayout(1, 3, 0, 0));
		footerPanel.setPreferredSize(new Dimension(0, 120));

		int r = 221;
		int g = 220;
		int b = 215;
		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		Color backgroundColor = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		footerPanel.setBackground(backgroundColor);

//		 Thông tin cửa hàng
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

//		 Liên hệ
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

//		 Thành viên
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

//		 Thêm footer vào dưới cùng
		pnlMain.add(footerPanel, BorderLayout.SOUTH);

		return pnlMain;
	}

	public JPanel LoadProDuctForCus(Products product) {
		this.setProCus(product);
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		pnlMain.setForeground(Color.WHITE);

		// NORTH: Tiêu đề (thêm giống LoadProDuct)
		pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblEdit = new JLabel("Chi tiết sản phẩm");
		lblEdit.setFont(new Font("Times New Roman", Font.BOLD, 40));
		pnlTitle.add(lblEdit);
		pnlMain.add(pnlTitle, BorderLayout.NORTH);

		// LEFT PANEL: Ảnh + Form chỉnh sửa
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		// Card vuông hiển thị ảnh và tên
		JPanel squareCard = new JPanel();
		squareCard.setLayout(new BoxLayout(squareCard, BoxLayout.Y_AXIS));
		squareCard.setPreferredSize(new Dimension(350, 300));
		squareCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, true));

		// Ảnh
		ImageIcon imgIcon = new ImageIcon(getClass().getResource(product.getProductImg()));
		Image img = imgIcon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Canh giữa ảnh

		// Tên sản phẩm
		lblname = new JLabel(product.getProductName());
		lblname.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblname.setAlignmentX(Component.CENTER_ALIGNMENT); // Canh giữa chữ
		lblname.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Cách ảnh 5px

		// Thêm vào card
		squareCard.add(imgLabel);
		squareCard.add(lblname);
		Box formBox = Box.createVerticalBox();
		formBox.add(Box.createVerticalStrut(20));
		leftPanel.add(Box.createVerticalStrut(10));
		leftPanel.add(squareCard);
		leftPanel.add(Box.createVerticalStrut(20));
		leftPanel.add(formBox);

		// RIGHT PANEL
		JPanel rightPanel = new JPanel();
		Font fo = new Font("Times New Roman", Font.BOLD, 25); 
		Box nameBox = Box.createHorizontalBox();
		JLabel lblpName;
		nameBox.add(lblpName = new JLabel("Tên sản phẩm: "));
		lblpName.setFont(fo);
		txtName = new JTextField(product.getProductName(), 20);
		txtName.setFont(fo);
		txtName.setEnabled(false); 
		nameBox.add(txtName);
		formBox.add(nameBox);
		formBox.add(Box.createVerticalStrut(20)); 

		// Giá
		Box priceBox = Box.createHorizontalBox();
		JLabel lblpPrice;
		priceBox.add(lblpPrice = new JLabel("Giá (VNĐ): "));
		lblpPrice.setFont(fo);

		// Format giá
		DecimalFormat formatter = new DecimalFormat("#,###");
		String formattedPrice = formatter.format(product.getPrice()) + "đ";

		txtPrice = new JTextField(formattedPrice, 20);
		txtPrice.setFont(fo);
		txtPrice.setEnabled(false);
		priceBox.add(txtPrice);
		formBox.add(priceBox);
		formBox.add(Box.createVerticalStrut(20)); 

		// Mô tả
		Box descBox = Box.createHorizontalBox();
		JLabel lblpDep;
		descBox.add(lblpDep = new JLabel("Mô tả: "));
		lblpDep.setFont(fo);
		txtDesc = new JTextField(product.getDescription(), 20);
		txtDesc.setFont(fo);
		txtDesc.setEnabled(false);
		descBox.add(txtDesc);
		formBox.add(descBox);
		formBox.add(Box.createVerticalStrut(20)); 
		// Size (ComboBox)
		Box sizeBox = Box.createHorizontalBox();
		JLabel lblpSize;
		sizeBox.add(lblpSize = new JLabel("Size: "));
		lblpSize.setFont(fo);
		String[] sizes;
		if (product.getProductTypeID().getProductTypeID() == 1) {
			sizes = new String[] { "Nhỏ", "Vừa", "Lớn" };
		} else {
			sizes = new String[] { "M", "L", "XL" };
		}
		cmbSize = new JComboBox<>(sizes);
		cmbSize.setFont(fo);
		cmbSize.setSelectedItem(product.getSize());
		sizeBox.add(cmbSize);
		formBox.add(sizeBox);
		formBox.add(Box.createVerticalStrut(20)); 

		// Buttons
		Box bBtn = Box.createHorizontalBox();
		bBtn.add(btnMinus = new JButton("-"));
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(lblQuantity = new JLabel("1"));
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(btnPlus = new JButton("+"));
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(btnAddToCart = new JButton("Thêm vào giỏ hàng"));
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(btnBack = new JButton("Quay lại"));
		lblQuantity.setFont(fo);
//		 Đặt màu cho các nút
		btnMinus.setBackground(new Color(239, 83, 80)); 
		btnMinus.setForeground(Color.WHITE);
		btnPlus.setBackground(new Color(102, 187, 106)); 
		btnPlus.setForeground(Color.WHITE);
		btnAddToCart.setBackground(new Color(79, 195, 247)); 
		btnAddToCart.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(176, 190, 197)); 
		btnBack.setForeground(Color.WHITE);

		// Đặt kích thước và màu cho các nút giống LoadProDuct
		for (JButton btn : new JButton[] { btnMinus, btnPlus, btnAddToCart, btnBack }) {
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btn.setFont(fo); // Font giống LoadProDuct
			btn.setPreferredSize(new Dimension(150, 40)); 
			btn.addActionListener(proCtrl);
			btn.setBorderPainted(false);
			
		}

		formBox.add(bBtn);

		lblpName.setPreferredSize(lblpName.getPreferredSize());
		lblpPrice.setPreferredSize(lblpName.getPreferredSize());
		lblpDep.setPreferredSize(lblpName.getPreferredSize());
		lblpSize.setPreferredSize(lblpName.getPreferredSize());

		rightPanel.add(formBox);

		// SPLIT PANE chia trái-phải
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setDividerLocation(400); // Khoảng cách giữa hai phần
		splitPane.setEnabled(false);
		splitPane.setDividerSize(0);

		pnlMain.add(splitPane, BorderLayout.CENTER);

		// Tạo footerPanel
		JPanel footerPanel = new JPanel(new GridLayout(1, 3, 0, 0));
		footerPanel.setPreferredSize(new Dimension(0, 120));

		int r = 221;
		int g = 220;
		int b = 215;
		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		Color backgroundColor = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		footerPanel.setBackground(backgroundColor);

//		 Thông tin cửa hàng
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

		// Liên hệ
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

		// Thành viên
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

		// Thêm footer vào dưới cùng
		pnlMain.add(footerPanel, BorderLayout.SOUTH);

		return pnlMain;
	}

}
