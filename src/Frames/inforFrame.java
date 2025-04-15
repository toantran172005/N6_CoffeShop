package Frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.inforCtrl;
import DAO.CustomerDAOIMP;
import DAO.EmployeeDAOIMP;
import Models.Customers;
import Models.Employees;

public class inforFrame {
	private int id;
	private CustomerDAOIMP cusDAO;
	private EmployeeDAOIMP empDAO;
	public JTextField txtAdd;
	public JTextField txtEmail;
	public JTextField txtName;
	public JTextField txtPhone;
	public JButton btnBack;
	public JButton btnChange;
	public JButton btnSave;
	public inforCtrl infctrl;

	public inforFrame(int id, customerFrame cusFrame) {
		this.id = id;
		cusDAO = new CustomerDAOIMP();
		empDAO = new EmployeeDAOIMP();
		infctrl = new inforCtrl(this, cusFrame);
	}

	public JPanel getInfPanel() {
		if (cusDAO.isCustomer(id) || this.id == 0)
			return this.cusInforPanel();
		else
			return this.empInforPanel();
	}

	public JPanel cusInforPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		Box bImg = Box.createVerticalBox();
		ImageIcon inforIcon = new ImageIcon(getClass().getResource("/Img/login.png"));
		Image inforImg = inforIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel lbImg = new JLabel(new ImageIcon(inforImg));
		JButton btnImg = new JButton("Chọn ảnh");
		lbImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		bImg.add(lbImg);
		bImg.add(Box.createVerticalStrut(10));
		bImg.add(btnImg);

		Box bInfor = Box.createHorizontalBox();
		JLabel lbInfor = new JLabel("Thông tin cá nhân");
		lbInfor.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbInfor.setAlignmentX(Component.CENTER_ALIGNMENT);
		bInfor.add(lbInfor);

		Box bPhone = Box.createHorizontalBox();
		JLabel lbPhone = new JLabel("Số liên lạc");
		lbPhone.setPreferredSize(new Dimension(100, 50));
		txtPhone = new JTextField();
		txtPhone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bPhone.add(lbPhone);
		bPhone.add(txtPhone);

		Box bName = Box.createHorizontalBox();
		JLabel lbName = new JLabel("Họ và tên");
		lbName.setPreferredSize(new Dimension(100, 50));
		txtName = new JTextField();
		txtName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bName.add(lbName);
		bName.add(txtName);

		Box bEmail = Box.createHorizontalBox();
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setPreferredSize(new Dimension(100, 50));
		txtEmail = new JTextField();
		txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bEmail.add(lbEmail);
		bEmail.add(txtEmail);

		Box bAdd = Box.createHorizontalBox();
		JLabel lbAdd = new JLabel("Địa chỉ");
		lbAdd.setPreferredSize(new Dimension(100, 50));
		txtAdd = new JTextField();
		txtAdd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bAdd.add(lbAdd);
		bAdd.add(txtAdd);

		this.setCusInfor();

		Box bBtn = Box.createHorizontalBox();
		btnBack = new JButton("Quay lại");
		btnChange = new JButton("Chỉnh sửa");
		btnSave = new JButton("Lưu");
		bBtn.add(btnChange);
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(btnSave);
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(btnBack);

		for (JLabel label : new JLabel[] { lbName, lbPhone, lbEmail, lbAdd }) {
			label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		}

		for (JButton btn : new JButton[] { btnImg, btnChange, btnSave, btnBack }) {
			btn.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btn.addActionListener(infctrl);
		}

		for (JTextField txt : new JTextField[] { txtName, txtPhone, txtEmail, txtAdd }) {
			txt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			txt.setEditable(false);
		}

		JPanel pnlContent = new JPanel();
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
		pnlContent.setPreferredSize(new Dimension(750, 748));
		pnlContent.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray, 1),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		pnlContent.add(bInfor);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bImg);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bName);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bPhone);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bEmail);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bAdd);
		pnlContent.add(bBtn);
		pnlContent.add(Box.createVerticalStrut(20));

		panel.add(pnlContent);

		return panel;
	}

	public JPanel empInforPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		Box bImg = Box.createVerticalBox();
		ImageIcon inforIcon = new ImageIcon(getClass().getResource("/Img/login.png"));
		Image inforImg = inforIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel lbImg = new JLabel(new ImageIcon(inforImg));
		JButton btnImg = new JButton("Chọn ảnh");
		lbImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		bImg.add(lbImg);
		bImg.add(Box.createVerticalStrut(10));
		bImg.add(btnImg);

		Box bInfor = Box.createHorizontalBox();
		JLabel lbInfor = new JLabel("Thông tin cá nhân");
		lbInfor.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbInfor.setAlignmentX(Component.CENTER_ALIGNMENT);
		bInfor.add(lbInfor);

		Box bPhone = Box.createHorizontalBox();
		JLabel lbPhone = new JLabel("Số liên lạc");
		lbPhone.setPreferredSize(new Dimension(100, 50));
		txtPhone = new JTextField();
		txtPhone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bPhone.add(lbPhone);
		bPhone.add(txtPhone);

		Box bName = Box.createHorizontalBox();
		JLabel lbName = new JLabel("Họ và tên");
		lbName.setPreferredSize(new Dimension(100, 50));
		txtName = new JTextField();
		txtName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bName.add(lbName);
		bName.add(txtName);

		Box bEmail = Box.createHorizontalBox();
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setPreferredSize(new Dimension(100, 50));
		txtEmail = new JTextField();
		txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bEmail.add(lbEmail);
		bEmail.add(txtEmail);

		Box bAdd = Box.createHorizontalBox();
		JLabel lbAdd = new JLabel("Địa chỉ");
		lbAdd.setPreferredSize(new Dimension(100, 50));
		txtAdd = new JTextField();
		txtAdd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		bAdd.add(lbAdd);
		bAdd.add(txtAdd);

		this.setEmpInfor();

		Box bBtn = Box.createHorizontalBox();
		btnBack = new JButton("Quay lại");
		btnChange = new JButton("Chỉnh sửa");
		btnSave = new JButton("Lưu");
		bBtn.add(btnChange);
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(btnSave);
		bBtn.add(Box.createHorizontalStrut(10));
		bBtn.add(btnBack);

		for (JLabel label : new JLabel[] { lbName, lbPhone, lbEmail, lbAdd }) {
			label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		}

		for (JButton btn : new JButton[] { btnImg, btnChange, btnSave, btnBack }) {
			btn.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btn.addActionListener(infctrl);
		}

		for (JTextField txt : new JTextField[] { txtName, txtPhone, txtEmail, txtAdd }) {
			txt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			txt.setEditable(false);
		}

		JPanel pnlContent = new JPanel();
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
		pnlContent.setPreferredSize(new Dimension(750, 748));
		pnlContent.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray, 1),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		pnlContent.add(bInfor);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bImg);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bName);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bPhone);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bEmail);
		pnlContent.add(Box.createVerticalStrut(20));
		pnlContent.add(bAdd);
		pnlContent.add(bBtn);
		pnlContent.add(Box.createVerticalStrut(20));

		panel.add(pnlContent);

		return panel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCusInfor() {
		if (this.id == 0) {
			this.txtName.setText("");
			this.txtPhone.setText("");
			this.txtEmail.setText("");
			this.txtAdd.setText("");
			return;
		} else {
			Customers cus = cusDAO.getCustomer(id);
			this.txtName.setText(cus.getCustomerName());
			this.txtPhone.setText(cus.getPhone());
			this.txtEmail.setText(cus.getEmail());
			this.txtAdd.setText(cus.getAddress());
			return;
		}
	}
	
	public void setEmpInfor() {
			Employees emp = empDAO.getEmployee(id);
			this.txtName.setText(emp.getEmployeeName());
			this.txtPhone.setText(emp.getPhone());
			this.txtEmail.setText(emp.getEmail());
			this.txtAdd.setText(emp.getAddress());
			return;
		}
}
