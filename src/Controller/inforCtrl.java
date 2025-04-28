package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DAO.CustomerDAOIMP;
import Frames.customerFrame;
import Frames.inforFrame;
import Models.Customers;

public class inforCtrl implements ActionListener {
	public CustomerDAOIMP cusDAO;
	public inforFrame infor;
	public customerFrame cusFrame;

	public inforCtrl(inforFrame infor, customerFrame cusFrame) {
		super();
		this.infor = infor;
		this.cusFrame = cusFrame;
		cusDAO = new CustomerDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JButton) {

			if (obj == this.infor.btnChange) {
				this.canEdit();
			} else if (obj == this.infor.btnBack) {
				this.cusFrame.updateProductPanel(this.cusFrame.getListProduct());
			} else if (obj == this.infor.btnSave) {
				Customers cus = new Customers(this.cusFrame.getCustomerID(), this.infor.txtName.getText().trim(), "",
						this.infor.txtPhone.getText().trim(), this.infor.txtEmail.getText().trim(),
						this.infor.txtAdd.getText().trim(), null);
				this.infor.btnSave.setEnabled(false);

				if (this.cusDAO.updateCustomerInfo(cus) == 0) {
					JOptionPane.showMessageDialog(this.cusFrame, "Cập nhập thành công!");
					SwingUtilities.invokeLater(() -> {
						this.cusFrame.changeToInfor();
		            });
					
				} else if(this.cusDAO.updateCustomerInfo(cus) == 1) { // Sai tên
					JOptionPane.showMessageDialog(this.cusFrame, "Vui lòng nhập đúng tên!");
					this.infor.txtName.selectAll();
					this.infor.txtName.requestFocusInWindow();
				}else if(this.cusDAO.updateCustomerInfo(cus) == 2) { // Sai email
					JOptionPane.showMessageDialog(this.cusFrame, "Vui lòng nhập đúng email!");
					this.infor.txtEmail.selectAll();
					this.infor.txtEmail.requestFocusInWindow();
				} else if(this.cusDAO.updateCustomerInfo(cus) == 3) { // Sai phone
					JOptionPane.showMessageDialog(this.cusFrame, "Vui lòng nhập đúng số liên lạc!");
					this.infor.txtPhone.selectAll();
					this.infor.txtPhone.requestFocusInWindow();
				} else if(this.cusDAO.updateCustomerInfo(cus) == 4) { // Sai Address
					JOptionPane.showMessageDialog(this.cusFrame, "Vui lòng nhập đúng địa chỉ!");
					this.infor.txtAdd.selectAll();
					this.infor.txtAdd.requestFocusInWindow();
				} else { // Lỗi không thể cập nhập
					JOptionPane.showMessageDialog(this.cusFrame, "Lỗi!");
				}
			} else if (obj == this.infor.btnImg) {

	            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
	            fileChooser.setDialogTitle("Chọn ảnh đại diện");
	            int result = fileChooser.showOpenDialog(null);
	            if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
	                String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
	                this.infor.selectedImagePath = imagePath; 

	                String relativePath = "/Img/" + new java.io.File(imagePath).getName();
	                this.infor.selectedImagePath = relativePath;

	                javax.swing.ImageIcon selectedIcon = new javax.swing.ImageIcon(imagePath);
	                java.awt.Image scaledImage = selectedIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
	                this.infor.lbImg.setIcon(new javax.swing.ImageIcon(scaledImage));
	            }
	        }

		}

	}

	public void canEdit() {
		this.infor.txtAdd.setEditable(true);
		this.infor.txtName.setEditable(true);
		this.infor.txtName.requestFocusInWindow();
		this.infor.txtPhone.setEditable(true);
		this.infor.txtEmail.setEditable(true);
		this.infor.btnSave.setEnabled(true);
	}

}
