package Controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import DAO.EmployeeDAOIMP;
import Frames.employeeFrame;
import Frames.productAddFrame;
import Models.Products;

public class proAddCtrl implements ActionListener {
    public productAddFrame addFrame;
	public employeeFrame emFrame;

    public proAddCtrl(productAddFrame addFrame, employeeFrame emFrame) {
        this.addFrame = addFrame;
        this.emFrame=emFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {
            JButton btn = (JButton) o;
            if (btn == addFrame.btnSave) {
                try {
                    String name = addFrame.txtName.getText().trim();
                    String priceText = addFrame.txtPrice.getText().trim();
                    String desc = addFrame.txtDesc.getText().trim();
                    String size = addFrame.cmbSize.getSelectedItem().toString();
                    String quantityText = addFrame.txtQuantity.getText().trim();

                    if (name.isEmpty() || priceText.isEmpty() || desc.isEmpty() || quantityText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                        return;
                    }

                    double price = Double.parseDouble(priceText);
                    int quantity = Integer.parseInt(quantityText);

                    Products newProduct = new Products();
                    newProduct.setProductName(name);
                    newProduct.setPrice(price);
                    newProduct.setDescription(desc);
                    newProduct.setSize(size);
                    newProduct.setQuantity(quantity);

                    String relativePath = "/Img/products/" + new File(addFrame.selectedImagePath).getName();
                    newProduct.setProductImg(relativePath); 
                    boolean success = new EmployeeDAOIMP().addProduct(newProduct);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                        addFrame.clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Giá và số lượng phải là số hợp lệ!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage());
                }
            } else if (btn == addFrame.btnChooseImg) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn ảnh sản phẩm");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                    addFrame.selectedImagePath = imagePath;

                    // Lấy địa chỉ tương đối
                    String relativePath = "/Img/" + new File(imagePath).getName();
                    addFrame.selectedImagePath = relativePath;

                    // Hiển thị ảnh đã chọn
                    ImageIcon selectedIcon = new ImageIcon(imagePath);
                    Image scaledImage = selectedIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                    addFrame.imgLabel.setIcon(new ImageIcon(scaledImage));
                }
            }else if (btn==addFrame.btnCancel) {
				this.emFrame.reloadEmployeePage();
			}
        }
    }

}
