package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.proDetailCtrl;
import Models.Products;

public class productDetailFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public productDetailFrame(Products product, int employeeID) {
			super("Nhóm 6 - CoffeeShop");
			this.products = product;    // Gán product đúng cách!
			this.employeeID= employeeID;
			this.proCtrl = new proDetailCtrl(this, product); // ✅ Gán trước
		}
	
	public JPanel LoadProDuct(Products product) {
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		pnlMain.setForeground(Color.WHITE);

//		 NOUTH: Tiêu đề
		pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblEdit = new JLabel("Chỉnh sửa sản phẩm");
		lblEdit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		pnlTitle.add(lblEdit);
		pnlMain.add(pnlTitle, BorderLayout.NORTH);

//		 LEFT PANEL: Ảnh + Form chỉnh sửa
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

//		 Card vuông hiển thị ảnh và tên
		JPanel squareCard = new JPanel();
		squareCard.setLayout(new BoxLayout(squareCard, BoxLayout.Y_AXIS));
		squareCard.setPreferredSize(new Dimension(350, 240));
		squareCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, true));

//		 Ảnh
		ImageIcon imgIcon = new ImageIcon(getClass().getResource(product.getProductImg()));
		Image img = imgIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
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
		Font fo =new Font("Arial", Font.BOLD, 17);
		Box nameBox = Box.createHorizontalBox();
		JLabel lblpName;
		nameBox.add(lblpName=new JLabel("Tên sản phẩm: "));
		lblpName.setFont(fo);
		txtName = new JTextField(product.getProductName(), 30);
		txtName.setFont(fo);
		txtName.setEnabled(false); // Ban đầu không cho phép chỉnh sửa
		nameBox.add(txtName);
		formBox.add(nameBox);
		formBox.add(Box.createVerticalStrut(10));

//		 Giá
		Box priceBox = Box.createHorizontalBox();
		JLabel lblpPrice;
		priceBox.add(lblpPrice=new JLabel("Giá (VNĐ): "));
		lblpPrice.setFont(fo);
		txtPrice = new JTextField(String.valueOf(product.getPrice()), 30);
		txtPrice.setFont(fo);
		txtPrice.setEnabled(false);
		priceBox.add(txtPrice);
		formBox.add(priceBox);
		formBox.add(Box.createVerticalStrut(10));

//		 Mô tả
		Box descBox = Box.createHorizontalBox();
		JLabel lblpDep;
		descBox.add(lblpDep=new JLabel("Mô tả: "));
		lblpDep.setFont(fo);
		txtDesc = new JTextField(product.getDescription(), 30);
		txtDesc.setFont(fo);
		txtDesc.setEnabled(false);
		descBox.add(txtDesc);
		formBox.add(descBox);
		formBox.add(Box.createVerticalStrut(10));

//		 Size (ComboBox)
		Box sizeBox = Box.createHorizontalBox();
		JLabel lblpSize;
		sizeBox.add(lblpSize=new JLabel("Size: "));
		lblpSize.setFont(fo);
		String[] sizes = { "Nhỏ", "Vừa", "Lớn" };
		cmbSize = new JComboBox<>(sizes);
		cmbSize.setFont(fo);	
		cmbSize.setSelectedItem(product.getSize());
		cmbSize.setEnabled(false); // Ban đầu không cho phép chỉnh sửa
		sizeBox.add(cmbSize);
		formBox.add(sizeBox);
		formBox.add(Box.createVerticalStrut(10));

//		 Số lượng tồn
		Box quantityBox = Box.createHorizontalBox();
		JLabel lblpQuan;
		quantityBox.add(lblpQuan=new JLabel("Số lượng tồn: "));
		lblpQuan.setFont(fo);
		txtQuantity = new JTextField(String.valueOf(product.getQuantity()), 30);
		txtQuantity.setFont(fo);
		txtQuantity.setEnabled(false);
		quantityBox.add(txtQuantity);
		formBox.add(quantityBox);
		formBox.add(Box.createVerticalStrut(20));
		
		lblpPrice.setPreferredSize(lblpName.getPreferredSize());
		lblpDep.setPreferredSize(lblpName.getPreferredSize());
		lblpSize.setPreferredSize(lblpName.getPreferredSize());
		lblpQuan.setPreferredSize(lblpName.getPreferredSize());

//		 Nút Sửa
		btnEdit = new JButton("Sửa");
		btnEdit.setFont(fo);
		btnEdit.addActionListener(proCtrl);
		
		formBox.add(btnEdit);

//		 Nút Lưu
		btnSave = new JButton("Lưu");
		btnSave.setFont(fo);
		btnSave.setEnabled(false); // Ban đầu không cho phép lưu
		btnSave.addActionListener(proCtrl);
		formBox.add(btnSave);
		formBox.add(Box.createVerticalStrut(10));
		rightPanel.add(formBox);
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
		        + "Thưởng thức hương vị cà phê đậm đà<br>"
		        + "Hãy đến trải nghiệm ngay hôm nay!</html>", SwingConstants.CENTER);
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
		        + "Email: Nhom6CoffeeShop@gmail.com<br>"
		        + "Số Liên Lạc: (+84) 123-456-789</html>", SwingConstants.CENTER);
		contactContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contactSection.add(contactTitle, BorderLayout.NORTH);
		contactSection.add(contactContent, BorderLayout.CENTER);
		footerPanel.add(contactSection);

//		 Thành viên
		JPanel memberSection = new JPanel(new BorderLayout());
		memberSection.setBackground(backgroundColor);
		JLabel memberTitle = new JLabel("Thành Viên", SwingConstants.CENTER);
		memberTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JLabel memberContent = new JLabel("<html>Trần Thanh Toàn - 23722181<br>Nguyễn Thành Tài - 23715171</html>", SwingConstants.CENTER);
		memberContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		memberSection.add(memberTitle, BorderLayout.NORTH);
		memberSection.add(memberContent, BorderLayout.CENTER);
		footerPanel.add(memberSection);

//		 Thêm footer vào dưới cùng
		pnlMain.add(footerPanel, BorderLayout.SOUTH);

		
		return pnlMain;
	}
	
	
}
