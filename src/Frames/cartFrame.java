package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.cartCtrl;
import DAO.CustomerDAOIMP;
import Models.CartItems;
import Models.Products;

public class cartFrame {
	private int id;
	@SuppressWarnings("unused")
	private customerFrame cusFrame;
	private CustomerDAOIMP cusDAO;
	private cartCtrl ctrl;
	public Map<JCheckBox, CartItems> mapCheckBox;
	public Map<Products, JLabel> mapQuantity;
	public Map<JButton, CartItems> mapPlus;
	public Map<JButton, CartItems> mapMinus;
	public Map<JButton, Products> mapDelete;
	public JCheckBox checkAll;
	public JLabel lblTotal;
	public JButton btnCheckout;

	public cartFrame(int id, customerFrame cusFrame) {
		super();
		this.id = id;
		this.cusFrame = cusFrame;
		this.cusDAO = new CustomerDAOIMP();
		this.ctrl = new cartCtrl(this, cusFrame);
		this.mapCheckBox = new HashMap<>(); 
		this.mapQuantity = new HashMap<>();
		this.mapPlus = new HashMap<>();
		this.mapMinus = new HashMap<>();
		this.mapDelete = new HashMap<>();
	}

	public JPanel getCartPanel() {
		if (cusDAO.isCustomer(id))
			return this.cartPanel();
		else
			return this.emptyCart();
	}

	public JPanel cartPanel() {

		JPanel mainPanel = new JPanel(new BorderLayout());

//         JPanel chứa danh sách sản phẩm
		JPanel pnlItems = new JPanel();
		pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.Y_AXIS));

		for (CartItems item : this.ctrl.getListItems()) {
			Products p = item.getProduct();
			int quantityVal = item.getQuantity();
//          JCheckBox để chọn sản phẩm
			JCheckBox checkBox = new JCheckBox();
			checkBox.setBackground(Color.white);
			checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			checkBox.addActionListener(ctrl);
			checkBox.addItemListener(ctrl);

//             Ảnh sản phẩm
			ImageIcon imgIcon = new ImageIcon(getClass().getResource(p.getProductImg()));
			Image img = imgIcon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
			JLabel imgLabel = new JLabel(new ImageIcon(img));

//             Tên + Giá
			JLabel lblName = new JLabel(p.getProductName());
			lblName.setFont(new Font("Times New Roman", Font.BOLD, 22));
			JLabel lblPrice = new JLabel(String.format("₫%,d", (int) p.getPrice()));
			lblPrice.setForeground(Color.RED);
			lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 18));
			Box boxName = Box.createVerticalBox();
			boxName.add(lblName);
			boxName.add(lblPrice);

//             Nút tăng giảm
			JButton btnMinus = new JButton("-");
			btnMinus.setFont(new Font("Times New Roman", Font.BOLD, 18));
			JLabel lblQuantity = new JLabel(String.valueOf(quantityVal));
			lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
			JButton btnPlus = new JButton("+");
			btnPlus.setFont(new Font("Times New Roman", Font.BOLD, 18));
			JButton btnDelete = new JButton();
			btnDelete.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Img/trash.png")).getImage()
					.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			btnDelete.setContentAreaFilled(false);
			btnDelete.setBorderPainted(false);
			btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnMinus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnPlus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnPlus.addActionListener(ctrl);
			btnMinus.addActionListener(ctrl);
			btnDelete.addActionListener(ctrl);

			Box quantityBox = Box.createHorizontalBox();
			quantityBox.add(btnMinus);
			quantityBox.add(Box.createHorizontalStrut(10));
			quantityBox.add(lblQuantity);
			quantityBox.add(Box.createHorizontalStrut(10));
			quantityBox.add(btnPlus);
			quantityBox.add(Box.createHorizontalStrut(10));
			quantityBox.add(btnDelete);
			
//          Ánh xạ JCheckBox với CartItems để tính tiền khi chọn
			mapCheckBox.put(checkBox, item);
            mapQuantity.put(p, lblQuantity);
			
//			Ánh xạ tăng giảm, xóa sản phẩm
            mapPlus.put(btnPlus, item);
            mapMinus.put(btnMinus, item);
            mapDelete.put(btnDelete, p);

			JPanel itemPanel = new JPanel(new BorderLayout());
			itemPanel.setBorder(
					BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
							BorderFactory.createEmptyBorder(10, 10, 10, 10)));

			JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			infoPanel.add(checkBox);
			infoPanel.add(Box.createHorizontalStrut(10));
			infoPanel.add(imgLabel);
			infoPanel.add(Box.createHorizontalStrut(20));
			infoPanel.add(boxName);
			infoPanel.setOpaque(false);

			itemPanel.add(infoPanel, BorderLayout.CENTER);
			itemPanel.add(quantityBox, BorderLayout.EAST);
			itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
			itemPanel.setBackground(Color.WHITE);
			itemPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 140));

			pnlItems.add(itemPanel);
		}

		mainPanel.add(pnlItems, BorderLayout.CENTER);

//         Footer 
		JPanel footer = new JPanel(new BorderLayout());
		footer.setPreferredSize(new Dimension(0, 100));
		footer.setBackground(Color.white);
		footer.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.LIGHT_GRAY),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

//        CheckBox tất cả
		checkAll = new JCheckBox("Tất cả");
		checkAll.setBackground(Color.white);
		checkAll.setFont(new Font("Times New Roman", Font.BOLD, 18));
		checkAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkAll.addActionListener(ctrl);

//        Label tổng tiền
		lblTotal = new JLabel("Tổng thanh toán: ₫0");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

//        Button mua hàng
		btnCheckout = new JButton("Mua hàng (0)");
		btnCheckout.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCheckout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		btnCheckout.setMaximumSize(new Dimension(150, 50));
		btnCheckout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		footer.add(checkAll, BorderLayout.WEST);
		JPanel pnl = new JPanel(new BorderLayout());
		pnl.add(lblTotal, BorderLayout.CENTER);
		Box btnBox = Box.createHorizontalBox();
		btnBox.add(Box.createHorizontalGlue());
		btnCheckout.setPreferredSize(new Dimension(150, 40));
		btnBox.add(btnCheckout);
		pnl.add(btnBox, BorderLayout.EAST);
		btnCheckout.setBackground(new Color(255, 77, 77));
		btnCheckout.setForeground(Color.black);
		btnCheckout.setBorderPainted(false);
		btnCheckout.addActionListener(ctrl);

		pnl.setBackground(Color.white);
		footer.add(pnl, BorderLayout.EAST);

		mainPanel.add(footer, BorderLayout.SOUTH);

		return mainPanel;
	}

	private JPanel emptyCart() {
		JLabel empty = new JLabel("Giỏ hàng trống");
		empty.setFont(new Font("Timen New Roman", Font.ITALIC, 80));
		JPanel pnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnl.add(empty);
		return pnl;
	}
}