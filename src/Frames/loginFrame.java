package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Controller.loginCtrl;

public class loginFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
	public loginFrame() {
        super("Đăng nhập");
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loginCtrl logCtrl = new loginCtrl(this);
        Font font = new Font("Times New Roman", Font.BOLD, 15);
        Box pnl_login = Box.createVerticalBox();

        Box b0, b1, b2, b3, b4;
        
        b0 = Box.createHorizontalBox();
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Img/login.png"));
        Image image = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel login = new JLabel(new ImageIcon(image));
        b0.add(login);
        
        
        b1 = Box.createHorizontalBox();
        JLabel lb_email = new JLabel("Nhập email");
        lb_email.setFont(font);
        lb_email.setPreferredSize(new Dimension(120, 30)); 
        JTextField txt_email = new JTextField();
        txt_email.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        b1.add(lb_email);
        b1.add(txt_email);

        b2 = Box.createHorizontalBox();
        JLabel lb_pwd = new JLabel("Nhập mật khẩu");
        lb_pwd.setFont(font);
        lb_pwd.setPreferredSize(new Dimension(120, 30)); 
        JPasswordField txt_pwd = new JPasswordField();
        txt_pwd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txt_pwd.setEchoChar('*');
        txt_email.addActionListener(e -> txt_pwd.requestFocus());
        b2.add(lb_pwd);
        b2.add(txt_pwd);

        b3 = Box.createHorizontalBox();
        JButton btn_login = new JButton("Đăng nhập");
        btn_login.addActionListener(logCtrl);
        btn_login.setFont(font);
        btn_login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        int r = 0, g = 150, b = 255;
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        btn_login.setForeground(Color.WHITE);
        btn_login.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        btn_login.setBorderPainted(false);
        btn_login.setOpaque(true);
        
        b3.add(btn_login);

        b4 = Box.createHorizontalBox();
        JLabel lb_dk = new JLabel("Chưa có tài khoản?");
        lb_dk.setFont(font);
        JButton btn_dk = new JButton("Đăng kí");
        btn_dk.addActionListener(logCtrl);
        btn_dk.setFont(font);
        lb_dk.setPreferredSize(new Dimension(140, btn_dk.getPreferredSize().height));
        btn_dk.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn_dk.getPreferredSize().height));
        btn_dk.setForeground(Color.WHITE);
        btn_dk.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        btn_dk.setBorderPainted(false);
        btn_dk.setOpaque(true);
        b4.add(lb_dk);
        b4.add(Box.createHorizontalGlue());
        b4.add(btn_dk);

        pnl_login.add(b0);
        pnl_login.add(Box.createVerticalStrut(20));
        pnl_login.add(b1);
        pnl_login.add(Box.createVerticalStrut(20));
        pnl_login.add(b2);
        pnl_login.add(Box.createVerticalStrut(20));
        pnl_login.add(b3);
        pnl_login.add(Box.createVerticalStrut(20)); 
        pnl_login.add(b4);

        JPanel pnl_empty = new JPanel(new BorderLayout());
        pnl_empty.setBorder(new EmptyBorder(10, 50, 100, 50));
        Box centerBox = Box.createHorizontalBox();
        centerBox.add(Box.createHorizontalGlue());
        centerBox.add(pnl_login);
        centerBox.add(Box.createHorizontalGlue());
        pnl_empty.add(centerBox, BorderLayout.CENTER);

        this.add(pnl_empty, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new loginFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}