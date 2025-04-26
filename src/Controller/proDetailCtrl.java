package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import DAO.EmployeeDAOIMP;
import Frames.employeeFrame;
import Frames.productDetailFrame;
import Models.Products;

public class proDetailCtrl implements ActionListener{
	public productDetailFrame proFrame;
	public Products product;
	public employeeFrame empFrame;

	public proDetailCtrl(productDetailFrame proFrame, Products product,employeeFrame empFrame) {
		super();
		this.product = product;
		this.proFrame = proFrame;
		this.empFrame=empFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if (o instanceof JButton) {
			JButton btn = (JButton) o;
			if (btn == this.proFrame.btnEdit) {
//	         Kích hoạt các field khi nhấn "Sửa"
				
	        this.proFrame.txtName.setEnabled(true);
	        this.proFrame.txtPrice.setEnabled(true);
	        this.proFrame.txtDesc.setEnabled(true);
	        this.proFrame.cmbSize.setEnabled(true);
	        this.proFrame.txtQuantity.setEnabled(true);

//	         Kích hoạt nút Lưu và tắt nút Sửa
	        this.proFrame.btnSave.setEnabled(true);
	        this.proFrame.btnEdit.setEnabled(false);
			}
			else if (btn == this.proFrame.btnSave) {
				try {
				product.setProductName(this.proFrame.txtName.getText().trim());
	            product.setPrice(Double.parseDouble(this.proFrame.txtPrice.getText().trim()));
	            product.setDescription(this.proFrame.txtDesc.getText().trim());
	            product.setSize(this.proFrame.cmbSize.getSelectedItem().toString());
	            product.setQuantity(Integer.parseInt(this.proFrame.txtQuantity.getText().trim()));
	            boolean success = new EmployeeDAOIMP().updateProduct(product); // Cập nhật qua DAO
	    	            if (success) {
	    	                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");   
	    	                proFrame.txtName.setEnabled(false);
	    	                proFrame.txtPrice.setEnabled(false);
	    	                proFrame.txtDesc.setEnabled(false);
	    	                proFrame.cmbSize.setEnabled(false);
	    	                proFrame.txtQuantity.setEnabled(false);

//	                 Tắt nút Lưu, kích hoạt nút Sửa lại
	    	                proFrame.btnSave.setEnabled(false);
	    	                proFrame.btnEdit.setEnabled(true);
	    	                }
	    	            else {
	    	            	JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
	    	            }
				}catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!");
				}
				
			}else if (btn == this.proFrame.btnDelete) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = new EmployeeDAOIMP().deleteProduct(product.getProductID());
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!");
                        this.empFrame.reloadEmployeePage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại!");
                    }
                }
            }
		}
	}
}	
	

