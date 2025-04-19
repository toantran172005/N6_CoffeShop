package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DAO.CustomerDAOIMP;
import Frames.customerFrame;
import Frames.loginFrame;
import Models.Products;

public class customerCtrl implements ActionListener, MouseListener, WindowListener {
	private customerFrame cusframe;
	private CustomerDAOIMP cusDAO;

	public customerCtrl(customerFrame cusframe) {
		super();
		this.cusframe = cusframe;
		cusDAO = new CustomerDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JButton) {
			JButton btn = (JButton) obj;
//			Tăng quantity
			if (this.cusframe.mapPlus.containsKey(btn)) {
				JLabel quantity = this.cusframe.mapPlus.get(btn);
				int qty = Integer.parseInt(quantity.getText());
				quantity.setText(String.valueOf(qty + 1));
//			Giảm quantity
			} else if (this.cusframe.mapMinus.containsKey(btn)) {
				JLabel quantity = this.cusframe.mapMinus.get(btn);
				int qty = Integer.parseInt(quantity.getText());
				if (qty > 1)
					quantity.setText(String.valueOf(qty - 1));
//				Thêm sản phẩm vào giỏ hàng
			} else if (this.cusframe.mapAddToCart.containsKey(btn)) {
				int quantity = Integer.valueOf(this.cusframe.mapQuantity.get(btn).getText());
				Products p = this.cusframe.mapAddToCart.get(btn);
				this.cusDAO.addProductToCart(this.cusframe.getCustomerID(), p, quantity);
			} else if (btn == this.cusframe.btnTatCa) {
				this.cusframe.updateProductPanel(this.cusframe.listProduct); // Hiện tất cả sản phẩm
			} else if (btn == this.cusframe.btnDrink) {
				ArrayList<Products> drinks = new ArrayList<>();
				for (Products p : this.cusframe.listProduct) {
					if (p.getProductTypeID().getProductTypeID() == 2) {
						drinks.add(p);
					}
				}
				this.cusframe.updateProductPanel(drinks); // Hiện đồ uống
			} else if (btn == this.cusframe.btnFood) {
				ArrayList<Products> foods = new ArrayList<>();
				for (Products p : this.cusframe.listProduct) {
					if (p.getProductTypeID().getProductTypeID() == 1) {
						foods.add(p);
					}
				}
				this.cusframe.updateProductPanel(foods); // Hiện đồ ăn
			} else if (obj == this.cusframe.btnTimKiem) {
				String search = this.cusframe.txtTimKiem.getText().trim();
				if (!search.isBlank()) {
					ArrayList<Products> listSearch = new ArrayList<>();
					Pattern pa = Pattern.compile(search.trim(), Pattern.CASE_INSENSITIVE);

					for (Products p : this.cusframe.getListProduct()) {
						Matcher m = pa.matcher(p.getProductName());
						if (m.find()) {
							listSearch.add(p);
						}
					}

					this.cusframe.updateProductPanel(listSearch);
				}

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JLabel) {
			JLabel label = (JLabel) obj;
//			Label cà phê
			if (label == this.cusframe.lbCoffee || label == this.cusframe.lbN6) {
				this.cusframe.updateProductPanel(this.cusframe.getListProduct());
//				label trang chủ
			} else if (label == this.cusframe.lblHome) {
				this.cusframe.updateProductPanel(this.cusframe.getListProduct());
				this.cusframe.isMenuAppear = !this.cusframe.isMenuAppear;
				this.cusframe.pnlMenu.setVisible(this.cusframe.isMenuAppear);
//				label menu
			} else if (label == this.cusframe.lbMenu) {
				this.cusframe.isMenuAppear = !this.cusframe.isMenuAppear;
				this.cusframe.pnlMenu.setVisible(this.cusframe.isMenuAppear);
//				label thông tin cá nhân
			} else if (label == this.cusframe.lblInfo) {
				this.cusframe.changeToInfor();
				this.cusframe.isMenuAppear = !this.cusframe.isMenuAppear;
				this.cusframe.pnlMenu.setVisible(this.cusframe.isMenuAppear);
//				label đăng nhâp
			} else if (label == this.cusframe.lblLogin) {
				this.cusframe.dispose();
				new loginFrame();
//				label đăng xuất
			} else if (label == this.cusframe.lblLogout) {
				this.cusframe.setCustomerID(0);
				JOptionPane.showMessageDialog(this.cusframe, "Đăng xuất thành công!");
				this.cusframe.isMenuAppear = !this.cusframe.isMenuAppear;
				this.cusframe.pnlMenu.setVisible(this.cusframe.isMenuAppear);
//				label giỏ hàng
			} else if(label == this.cusframe.lbCart) {
				this.cusframe.changToCart();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.cusDAO.closeDatabase();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
