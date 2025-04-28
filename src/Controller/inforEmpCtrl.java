package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DAO.EmployeeDAOIMP;
import Frames.employeeFrame;
import Frames.inforFrame;
import Models.Employees;

public class inforEmpCtrl implements ActionListener {
	public EmployeeDAOIMP empDAO;
	public inforFrame infor;
	public employeeFrame empFrame;

	public inforEmpCtrl(inforFrame infor, employeeFrame empFrame) {
		super();
		this.infor = infor;
		this.empFrame = empFrame;
		empDAO = new EmployeeDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JButton) {

			if (obj == this.infor.btnChange) {
				this.canEdit();
			} else if (obj == this.infor.btnBack) {
				this.empFrame.updateProductPanel(this.empFrame.getListProduct());
			} else if (obj == this.infor.btnSave) {
				Employees emp = new Employees(this.empFrame.getEmployeeID(), this.infor.txtName.getText().trim(),
						this.infor.txtPhone.getText().trim(), this.infor.txtEmail.getText().trim(),
						this.infor.txtAdd.getText().trim(), null);
				this.infor.btnSave.setEnabled(false);
				if (this.empDAO.updateEmployeeInfo(emp) == 0) {
					JOptionPane.showMessageDialog(this.empFrame, "Cập nhập thành công!");
					SwingUtilities.invokeLater(() -> {
						this.empFrame.changeToInfor();
		            });
					
				} else if (this.empDAO.updateEmployeeInfo(emp) == 1) { // Sai tên
					JOptionPane.showMessageDialog(this.empFrame, "Vui lòng nhập đúng tên!");
					this.infor.txtName.selectAll();
					this.infor.txtName.requestFocusInWindow();
				} else if (this.empDAO.updateEmployeeInfo(emp) == 2) { // Sai email
					JOptionPane.showMessageDialog(this.empFrame, "Vui lòng nhập đúng email!");
					this.infor.txtEmail.selectAll();
					this.infor.txtEmail.requestFocusInWindow();
				} else if (this.empDAO.updateEmployeeInfo(emp) == 3) { // Sai phone
					JOptionPane.showMessageDialog(this.empFrame, "Vui lòng nhập đúng số liên lạc!");
					this.infor.txtPhone.selectAll();
					this.infor.txtPhone.requestFocusInWindow();
				} else if (this.empDAO.updateEmployeeInfo(emp) == 4) { // Sai Address
					JOptionPane.showMessageDialog(this.empFrame, "Vui lòng nhập đúng địa chỉ!");
					this.infor.txtAdd.selectAll();
					this.infor.txtAdd.requestFocusInWindow();
				} else { // Lỗi không thể cập nhập
					JOptionPane.showMessageDialog(this.empFrame, "Lỗi!");
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
		this.infor.txtName.requestFocusInWindow();
		this.infor.txtName.setEditable(true);
		this.infor.txtPhone.setEditable(true);
		this.infor.txtEmail.setEditable(true);
		this.infor.btnSave.setEnabled(true);
	}

}
