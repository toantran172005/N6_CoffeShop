package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.orderCtrl;
import Models.CartItems;
import Models.Products;

public class orderFrame {
	public orderCtrl ordCtrl;
	public ArrayList<CartItems> list;
	public JButton btnBack;
	public JButton btnCash;
	public JButton btnTransfer;
	public int orderTotal;
	
	public ArrayList<CartItems> getList() {
		return list;
	}

	public void setList(ArrayList<CartItems> list) {
		this.list = list;
	}

	public orderFrame(customerFrame cusFrame, ArrayList<CartItems> list) {
		ordCtrl = new orderCtrl(this, cusFrame);
		this.list = list;
	}

	public JPanel getOrderPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);

//         JPanel chứa danh sách sản phẩm
		JPanel pnlItems = new JPanel();
		pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.Y_AXIS));
		pnlItems.setBackground(Color.WHITE);

//         Header 
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setBackground(Color.LIGHT_GRAY);
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

		JLabel lblSTT = new JLabel("STT");
		lblSTT.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSTT.setPreferredSize(new Dimension(50, 30));

		JLabel lblProductName = new JLabel("Tên sản phẩm");
		lblProductName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblProductName.setPreferredSize(new Dimension(200, 30));

		JLabel lblQuantity = new JLabel("Số lượng");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblQuantity.setPreferredSize(new Dimension(100, 30));

		JLabel lblPrice = new JLabel("Đơn giá");
		lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPrice.setPreferredSize(new Dimension(100, 30));

		JLabel lblTotalPrice = new JLabel("Thành tiền");
		lblTotalPrice.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTotalPrice.setPreferredSize(new Dimension(150, 30));

		JLabel lblNote = new JLabel("Ghi chú");
		lblNote.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNote.setPreferredSize(new Dimension(150, 30));

		headerPanel.add(lblSTT);
		headerPanel.add(Box.createHorizontalStrut(10));
		headerPanel.add(lblProductName);
		headerPanel.add(Box.createHorizontalStrut(10));
		headerPanel.add(lblQuantity);
		headerPanel.add(Box.createHorizontalStrut(10));
		headerPanel.add(lblPrice);
		headerPanel.add(Box.createHorizontalStrut(10));
		headerPanel.add(lblTotalPrice);
		headerPanel.add(Box.createHorizontalStrut(10));
		headerPanel.add(lblNote);

		pnlItems.add(headerPanel);

		int stt = 1;
		orderTotal = 0; // Tổng hóa đơn
		for (CartItems item : list) {
			Products p = item.getProduct();
			int quantityVal = item.getQuantity();
			double price = p.getPrice();
			double totalPrice = price * quantityVal; // Thành tiền
			orderTotal += totalPrice; // Cộng vào tổng hóa đơn

//             Tạo panel cho mỗi sản phẩm
			JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			itemPanel.setBackground(Color.WHITE);
			itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
			itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

			JLabel lblItemSTT = new JLabel(String.valueOf(stt++));
			lblItemSTT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblItemSTT.setPreferredSize(new Dimension(50, 30));

			JLabel lblItemName = new JLabel(p.getProductName());
			lblItemName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblItemName.setPreferredSize(new Dimension(200, 30));

			JLabel lblItemQuantity = new JLabel(String.valueOf(quantityVal));
			lblItemQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblItemQuantity.setPreferredSize(new Dimension(100, 30));

			JLabel lblItemPrice = new JLabel(String.format("₫%,d", (int) price));
			lblItemPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblItemPrice.setPreferredSize(new Dimension(100, 30));

			JLabel lblItemTotalPrice = new JLabel(String.format("₫%,d", (int) totalPrice));
			lblItemTotalPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblItemTotalPrice.setPreferredSize(new Dimension(150, 30));

			itemPanel.add(lblItemSTT);
			itemPanel.add(Box.createHorizontalStrut(10));
			itemPanel.add(lblItemName);
			itemPanel.add(Box.createHorizontalStrut(10));
			itemPanel.add(lblItemQuantity);
			itemPanel.add(Box.createHorizontalStrut(10));
			itemPanel.add(lblItemPrice);
			itemPanel.add(Box.createHorizontalStrut(10));
			itemPanel.add(lblItemTotalPrice);

			pnlItems.add(itemPanel);
		}

//         Tổng hóa đơn
		JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		totalPanel.setBackground(Color.WHITE);
		totalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
		totalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

		JLabel lblorderTotalLabel = new JLabel("Tổng hóa đơn: ");
		lblorderTotalLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		JLabel lblorderTotal = new JLabel(String.format("₫%,d", (int) orderTotal));
		lblorderTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblorderTotal.setForeground(Color.RED);

		totalPanel.add(lblorderTotalLabel);
		totalPanel.add(lblorderTotal);

//         Thêm danh sách sản phẩm và tổng hóa đơn vào mainPanel
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(pnlItems, BorderLayout.CENTER);
		contentPanel.add(totalPanel, BorderLayout.SOUTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);

//         Footer
		JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		footer.setPreferredSize(new Dimension(0, 60));
		footer.setBackground(Color.WHITE);
		footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

		btnBack = new JButton("Quay lại");
		btnBack.setPreferredSize(new Dimension(150, 40));
		btnBack.setBackground(new Color(200, 200, 200));
		btnBack.setBorderPainted(false);

		btnCash = new JButton("Thanh toán bằng tiền mặt");
		btnCash.setPreferredSize(new Dimension(250, 40));
		btnCash.setBackground(new Color(255, 77, 77));
		btnCash.setForeground(Color.BLACK);
		btnCash.setBorderPainted(false);

		btnTransfer = new JButton("Chuyển khoản");
		btnTransfer.setPreferredSize(new Dimension(150, 40));
		btnTransfer.setBackground(new Color(50, 150, 50));
		btnTransfer.setForeground(Color.WHITE);
		btnTransfer.setBorderPainted(false);

		for (JButton btn : new JButton[] { btnBack, btnCash, btnTransfer }) {
			btn.addActionListener(ordCtrl);
			btn.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		footer.add(btnBack);
		footer.add(btnCash);
		footer.add(btnTransfer);

		mainPanel.add(footer, BorderLayout.SOUTH);

		return mainPanel;
	}
	
	public JPanel getQRPay() {
	    JPanel qrPanel = new JPanel(new BorderLayout());
	    qrPanel.setBackground(Color.WHITE);
	    ImageIcon qrIcon = new ImageIcon(getClass().getResource("/Img/QR.png"));
	    Image qrImg = qrIcon.getImage().getScaledInstance(750, 600, Image.SCALE_SMOOTH);
	    JLabel qrLabel = new JLabel(new ImageIcon(qrImg));
	    qrPanel.add(qrLabel, BorderLayout.CENTER);
	    
	    return qrPanel;
	}
	
	
}