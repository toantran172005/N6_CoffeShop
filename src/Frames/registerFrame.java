package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Controller.registerCtrl;

public class registerFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public registerFrame() {
		super("Đăng kí");
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		registerCtrl reCtrl = new registerCtrl(this);
		Font font = new Font("Times New Roman", Font.BOLD, 15);
		JPanel pnl_register = new JPanel();
		pnl_register.setLayout(new BoxLayout(pnl_register, BoxLayout.Y_AXIS));

		JLabel lb_cusName = new JLabel("Nhập họ và tên");
		lb_cusName.setFont(font);
		lb_cusName.setPreferredSize(new Dimension(110, 40));
		JTextField txt_cusName = new JTextField();
		txt_cusName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		txt_cusName.requestFocusInWindow();

		JLabel lb_email = new JLabel("Nhập email");
		lb_email.setFont(font);
		lb_email.setPreferredSize(new Dimension(110, 40));
		JTextField txt_email = new JTextField();
		txt_email.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

		JLabel lb_pwd = new JLabel("Nhập mật khẩu");
		lb_pwd.setFont(font);
		lb_pwd.setPreferredSize(new Dimension(110, 40));
		JPasswordField txt_pwd = new JPasswordField();
		txt_pwd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		txt_pwd.setEchoChar('*');

		JLabel lb_phone = new JLabel("Nhập số liên lạc");
		lb_phone.setFont(font);
		lb_phone.setPreferredSize(new Dimension(110, 40));
		JTextField txt_phone = new JTextField();
		txt_phone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

		JLabel lb_address = new JLabel("Nhập địa chỉ");
		lb_address.setFont(font);
		lb_address.setPreferredSize(new Dimension(110, 40));
		JTextField txt_address = new JTextField();
		txt_address.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));


		txt_cusName.addActionListener(e -> txt_email.requestFocus());
		txt_email.addActionListener(e -> txt_pwd.requestFocus());
		txt_pwd.addActionListener(e -> txt_phone.requestFocus());
		txt_phone.addActionListener(e -> txt_address.requestFocus());

		Box b1 = Box.createHorizontalBox();
		b1.add(Box.createHorizontalStrut(10));
		b1.add(lb_cusName);
		b1.add(txt_cusName);

		Box b2 = Box.createHorizontalBox();
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lb_email);
		b2.add(txt_email);

		Box b3 = Box.createHorizontalBox();
		b3.add(Box.createHorizontalStrut(10));
		b3.add(lb_pwd);
		b3.add(txt_pwd);

		Box b4 = Box.createHorizontalBox();
		b4.add(Box.createHorizontalStrut(10));
		b4.add(lb_phone);
		b4.add(txt_phone);

		Box b5 = Box.createHorizontalBox();
		b5.add(Box.createHorizontalStrut(10));
		b5.add(lb_address);
		b5.add(txt_address);

		pnl_register.add(b1);
		pnl_register.add(Box.createVerticalStrut(20));
		pnl_register.add(b2);
		pnl_register.add(Box.createVerticalStrut(20));
		pnl_register.add(b3);
		pnl_register.add(Box.createVerticalStrut(20));
		pnl_register.add(b4);
		pnl_register.add(Box.createVerticalStrut(20));
		pnl_register.add(b5);

		int r = 0;
		int g = 150;
		int b = 255;
		float[] hsb = Color.RGBtoHSB(r, g, b, null);
		Box b6 = Box.createHorizontalBox();
		JLabel lb_dk = new JLabel("Đăng kí tài khoản");
		lb_dk.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
		lb_dk.setFont(new Font("Times New Roman", Font.BOLD, 30));
		b6.add(new JPanel().add(lb_dk));

		Box b7 = Box.createHorizontalBox();
		JLabel lb_n6 = new JLabel("Nhóm 6 - Coffeeshop");
		lb_n6.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
		lb_n6.setFont(new Font("Times New Roman", Font.BOLD, 30));
		b7.add(new JPanel().add(lb_n6));


		Font fontHD = new Font("Times New Roman", Font.BOLD, 17);
		Box b9 = Box.createVerticalBox();
		JLabel lb_hd = new JLabel("HƯỚNG DẪN ĐĂNG KÍ");
		JLabel lb_emailHD = new JLabel("Email không được bắt đầu bằng '.' hoặc '_' ");
		JLabel lb_pwdHD = new JLabel("Mật khẩu phải có đủ 8 kí tự");
		JLabel lb_pwdHD2 = new JLabel("   (Gồm ít nhất 1 chữ HOA, 1 kí tự đặc biệt và 1 số)   ");
		JLabel lb_phoneHD = new JLabel("Số liên lạc phải có 10 chữ số");
		lb_hd.setFont(fontHD);
		lb_emailHD.setFont(fontHD);
		lb_pwdHD.setFont(fontHD);
		lb_pwdHD2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lb_phoneHD.setFont(fontHD);
		Box b90 = Box.createHorizontalBox();
		b90.add(lb_hd);
		Box b91 = Box.createHorizontalBox();
		b91.add(lb_emailHD);
		Box b92 = Box.createHorizontalBox();
		b92.add(lb_pwdHD);
		Box b93 = Box.createHorizontalBox();
		b93.add(lb_pwdHD2);
		Box b94 = Box.createHorizontalBox();
		b94.add(lb_phoneHD);
		b9.add(b90);
		b9.add(Box.createVerticalStrut(10));
		b9.add(b91);
		b9.add(b92);
		b9.add(b93);
		b9.add(b94);

		Box b_dk = Box.createVerticalBox();
		b_dk.add(b6);
		b_dk.add(b7);
		b_dk.add(Box.createVerticalStrut(10));
		b_dk.add(b9);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, b_dk, pnl_register);

		JPanel emptyBottom = new JPanel();
		emptyBottom.setLayout(new BoxLayout(emptyBottom, BoxLayout.Y_AXIS));

		JButton btn_register = new JButton("Đăng kí");
		btn_register.addActionListener(reCtrl);
		btn_register.setForeground(Color.WHITE);
		btn_register.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
		btn_register.setBorderPainted(false);
		btn_register.setOpaque(true);
		btn_register.setFont(font);
		btn_register.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn_register.getPreferredSize().height));
		btn_register.setAlignmentX(CENTER_ALIGNMENT);

		Box b8 = Box.createHorizontalBox();
		JLabel lb_login = new JLabel("Đã có tài khoản?");
		lb_login.setFont(font);
		JButton btn_login = new JButton("Đăng nhập");
		btn_login.addActionListener(reCtrl);
		btn_login.setForeground(Color.WHITE);
		btn_login.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
		btn_login.setBorderPainted(false);
		btn_login.setOpaque(true);
		btn_login.setFont(font);
		btn_login.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn_login.getPreferredSize().height));
		b8.add(lb_login);
		b8.add(Box.createHorizontalStrut(10));
		b8.add(btn_login);

		emptyBottom.add(Box.createVerticalStrut(10));
		emptyBottom.add(btn_register);
		emptyBottom.add(Box.createVerticalStrut(10));
		emptyBottom.add(b8);

		JPanel pnl_empty = new JPanel(new BorderLayout());
		pnl_empty.setBorder(new EmptyBorder(100, 50, 150, 50));

		pnl_empty.add(split, BorderLayout.CENTER);
		pnl_empty.add(emptyBottom, BorderLayout.SOUTH);

		this.add(pnl_empty, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new registerFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}