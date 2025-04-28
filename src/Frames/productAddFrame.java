package Frames;

import java.awt.*;
import javax.swing.*;
import Controller.proAddCtrl;

public class productAddFrame{
	public JPanel pnlMain;
	public JLabel lblAdd;
	public JButton btnSave;
	public JButton btnCancel;
	public JTextField txtName;
	public JTextField txtPrice;
	public JTextArea txtDesc;
	public JComboBox<String> cmbSize;
	public JTextField txtQuantity;
	public proAddCtrl addCtrl;
	public int employeeID;
	public JButton btnChooseImg;
	public String selectedImagePath = "/Img/defaultImage.png";
	public JLabel imgLabel;
	public employeeFrame empFrame;
	public int productTypeID;


	public productAddFrame(int employeeID, employeeFrame empFrame, int productTypeID) {
	    this.empFrame = empFrame;
	    this.employeeID = employeeID;
	    this.productTypeID = productTypeID;
	    this.addCtrl = new proAddCtrl(this, empFrame);
	}


	public JPanel loadAddProductPanel() {
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

		// Tiêu đề
		JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblAdd = new JLabel("Thêm sản phẩm mới");
		lblAdd.setFont(new Font("Times New Roman", Font.BOLD, 40));
		pnlTitle.add(lblAdd);
		pnlMain.add(pnlTitle, BorderLayout.NORTH);

		// Panel trái: Ảnh mẫu
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
		
		JPanel squareCard = new JPanel();
		squareCard.setLayout(new BoxLayout(squareCard, BoxLayout.Y_AXIS));
		squareCard.setPreferredSize(new Dimension(350, 240));
		squareCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 0, true));

		// Ảnh mặc định
		ImageIcon defaultIcon = new ImageIcon(getClass().getResource(selectedImagePath));
		Image scaledImage = defaultIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		imgLabel = new JLabel(new ImageIcon(scaledImage));
		imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		


		JLabel lblDefault = new JLabel("Hình ảnh sản phẩm");
		lblDefault.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblDefault.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDefault.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		leftPanel.add(Box.createVerticalStrut(5));
		leftPanel.add(Box.createHorizontalStrut(5));
		leftPanel.add(squareCard);
		//Chọn ảnh
		btnChooseImg = new JButton("Chọn ảnh");
		btnChooseImg.setFont(new Font("Time New Roman", Font.BOLD, 22));
		btnChooseImg.setBackground(new Color(0,102,102));
		btnChooseImg.setForeground(Color.WHITE);
		btnChooseImg.setBorderPainted(false);
		btnChooseImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChooseImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnChooseImg.addActionListener(addCtrl);
		squareCard.add(imgLabel);
		squareCard.add(lblDefault);
		squareCard.add(Box.createVerticalStrut(10));
		squareCard.add(btnChooseImg);
		// Panel phải: Form
		JPanel rightPanel = new JPanel();
		Box formBox = Box.createVerticalBox();
		Font font = new Font("Arial", Font.BOLD, 25);
		formBox.add(Box.createVerticalStrut(50));
		Box nameBox = Box.createHorizontalBox();
		JLabel lblName = new JLabel("Tên sản phẩm: ");
		lblName.setFont(font);
		txtName = new JTextField(20);
		txtName.setFont(font);
		nameBox.add(lblName);
		nameBox.add(txtName);
		formBox.add(nameBox);
		formBox.add(Box.createVerticalStrut(10));

		Box priceBox = Box.createHorizontalBox();
		JLabel lblPrice = new JLabel("Giá (VNĐ): ");
		lblPrice.setFont(font);
		txtPrice = new JTextField(20);
		txtPrice.setFont(font);
		priceBox.add(lblPrice);
		priceBox.add(txtPrice);
		formBox.add(priceBox);
		formBox.add(Box.createVerticalStrut(10));

		Box descBox = Box.createHorizontalBox();
		JLabel lblDesc = new JLabel("Mô tả: ");
		lblDesc.setFont(font);
		txtDesc = new JTextArea(4, 20);
		txtDesc.setFont(font);
		txtDesc.setLineWrap(true);
		txtDesc.setWrapStyleWord(true);
		JScrollPane scrollDesc = new JScrollPane(txtDesc);
		scrollDesc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollDesc.setPreferredSize(new Dimension(400, 80));
		descBox.add(lblDesc);
		descBox.add(scrollDesc);
		formBox.add(descBox);
		formBox.add(Box.createVerticalStrut(10));

		Box sizeBox = Box.createHorizontalBox();
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(font);
		String[] sizes;
		if (productTypeID == 1) {
		    sizes = new String[]{"Nhỏ", "Vừa", "Lớn"};
		} else {
		    sizes = new String[]{"M", "L", "XL"};
		}
		cmbSize = new JComboBox<>(sizes);
		cmbSize.setFont(font);
		sizeBox.add(lblSize);
		sizeBox.add(cmbSize);
		formBox.add(sizeBox);
		formBox.add(Box.createVerticalStrut(10));

		Box quantityBox = Box.createHorizontalBox();
		JLabel lblQuantity = new JLabel("Số lượng tồn: ");
		lblQuantity.setFont(font);
		txtQuantity = new JTextField(20);
		txtQuantity.setFont(font);
		quantityBox.add(lblQuantity);
		quantityBox.add(txtQuantity);
		formBox.add(quantityBox);
		formBox.add(Box.createVerticalStrut(20));

		lblPrice.setPreferredSize(lblName.getPreferredSize());
		lblDesc.setPreferredSize(lblName.getPreferredSize());
		lblSize.setPreferredSize(lblName.getPreferredSize());
		lblQuantity.setPreferredSize(lblName.getPreferredSize());
		
		//footer
		JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		footer.setPreferredSize(new Dimension(0, 60));
		//Nút thêm
		btnSave = new JButton("Thêm");
		btnSave.setFont(font);
		btnSave.setPreferredSize(new Dimension(150, 40));
		btnSave.setBackground(new Color(0,102,51));
		btnSave.setForeground(Color.WHITE);
		btnSave.setBorderPainted(false);
		btnSave.addActionListener(addCtrl);
		//Quay lại
		btnCancel = new JButton("Quay lại");
		btnCancel.setFont(font);
		btnCancel.setPreferredSize(new Dimension(150, 40));
		btnCancel.setBackground(new Color(200, 200, 200));
		btnCancel.setBorderPainted(false);
		btnCancel.addActionListener(addCtrl);
		footer.add(btnSave);
		footer.add(btnCancel);
		formBox.add(footer);
		rightPanel.add(formBox);

		// Chia panel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setDividerLocation(400);
		splitPane.setEnabled(false);
		splitPane.setDividerSize(0);
		pnlMain.add(splitPane, BorderLayout.CENTER);

		// Footer
		JPanel footerPanel = new JPanel(new GridLayout(1, 3, 0, 0));
		footerPanel.setPreferredSize(new Dimension(0, 120));
		Color bgColor = new Color(221, 220, 215);
		footerPanel.setBackground(bgColor);

		JPanel infoSection = new JPanel(new BorderLayout());
		infoSection.setBackground(bgColor);
		JLabel infoTitle = new JLabel("Nhóm 6 - Coffee Shop", SwingConstants.CENTER);
		infoTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JLabel infoContent = new JLabel("<html>Đem đến trải nghiệm tốt nhất<br>"
				+ "Thưởng thức hương vị cà phê đậm đà<br>"
				+ "Hãy đến trải nghiệm ngay hôm nay!</html>", SwingConstants.CENTER);
		infoContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		infoSection.add(infoTitle, BorderLayout.NORTH);
		infoSection.add(infoContent, BorderLayout.CENTER);

		JPanel contactSection = new JPanel(new BorderLayout());
		contactSection.setBackground(bgColor);
		JLabel contactTitle = new JLabel("Liên Hệ", SwingConstants.CENTER);
		contactTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JLabel contactContent = new JLabel("<html>Địa chỉ: Trường Đại Học Công Nghiệp TP.HCM<br>"
				+ "Email: Nhom6CoffeeShop@gmail.com<br>"
				+ "Số Liên Lạc: (+84) 123-456-789</html>", SwingConstants.CENTER);
		contactContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contactSection.add(contactTitle, BorderLayout.NORTH);
		contactSection.add(contactContent, BorderLayout.CENTER);

		JPanel memberSection = new JPanel(new BorderLayout());
		memberSection.setBackground(bgColor);
		JLabel memberTitle = new JLabel("Thành Viên", SwingConstants.CENTER);
		memberTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JLabel memberContent = new JLabel("<html>Trần Thanh Toàn - 23722181<br>Nguyễn Thành Tài - 23715171</html>", SwingConstants.CENTER);
		memberContent.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		memberSection.add(memberTitle, BorderLayout.NORTH);
		memberSection.add(memberContent, BorderLayout.CENTER);

		footerPanel.add(infoSection);
		footerPanel.add(contactSection);
		footerPanel.add(memberSection);

		pnlMain.add(footerPanel, BorderLayout.SOUTH);

		return pnlMain;
	}
	public void clearFields() {
	    txtName.setText("");
	    txtPrice.setText("");
	    txtDesc.setText("");
	    txtQuantity.setText("");
	    cmbSize.setSelectedIndex(0); // reset về "Nhỏ"
	}

}
