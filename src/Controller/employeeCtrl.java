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
import javax.swing.SwingUtilities;

import DAO.EmployeeDAOIMP;
import Frames.customerFrame;
import Frames.employeeFrame;
import Frames.loginFrame;
import Models.Products;

public class employeeCtrl implements ActionListener, MouseListener, WindowListener {
	private employeeFrame empFrame;
	private EmployeeDAOIMP empDAO;

	public employeeCtrl(employeeFrame empFrame) {
		super();
		this.empFrame = empFrame;
		empDAO = new EmployeeDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JButton) {
			JButton btn = (JButton) obj;
			if (btn == this.empFrame.btnTatCa) {
				this.empFrame.updateProductPanel(this.empFrame.listProduct); // Hiện tất cả sản phẩm
			} else if (btn == this.empFrame.btnDrink) {
				ArrayList<Products> drinks = new ArrayList<>();
				for (Products p : this.empFrame.listProduct) {
					if (p.getProductTypeID().getProductTypeID() == 2) {
						drinks.add(p);
					}
				}
				this.empFrame.updateProductPanel(drinks); // Hiện đồ uống
			} else if (btn == this.empFrame.btnFood) {
				ArrayList<Products> foods = new ArrayList<>();
				for (Products p : this.empFrame.listProduct) {
					if (p.getProductTypeID().getProductTypeID() == 1) {
						foods.add(p);
					}
				}
				this.empFrame.updateProductPanel(foods); // Hiện đồ ăn
			} else if (obj == this.empFrame.btnTimKiem) {
				String search = this.empFrame.txtTimKiem.getText().trim();
				if (!search.isBlank()) {
					ArrayList<Products> listSearch = new ArrayList<>();
					Pattern pa = Pattern.compile(search.trim(), Pattern.CASE_INSENSITIVE);

					for (Products p : this.empFrame.getListProduct()) {
						Matcher m = pa.matcher(p.getProductName());
						if (m.find()) {
							listSearch.add(p);
						}
					}

					this.empFrame.updateProductPanel(listSearch);
				}

			} else if (this.empFrame.mapProduct.containsKey(btn)) { // chỉnh sửa sản phẩm
				Products product = this.empFrame.mapProduct.get(btn);
				SwingUtilities.invokeLater(() -> {
					this.empFrame.changeToDetail(product);
				});

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JLabel) {
			JLabel label = (JLabel) obj;
//			Label cà phê
			if (label == this.empFrame.lbCoffee || label == this.empFrame.lbN6) {
				this.empFrame.reloadEmployeePage();
//				label trang chủ
			} else if (label == this.empFrame.lblHome) {
				this.empFrame.reloadEmployeePage();
				this.empFrame.isMenuAppear = !this.empFrame.isMenuAppear;
				this.empFrame.pnlMenu.setVisible(this.empFrame.isMenuAppear);
//				label menu
			} else if (label == this.empFrame.lbMenu) {
				this.empFrame.isMenuAppear = !this.empFrame.isMenuAppear;
				this.empFrame.pnlMenu.setVisible(this.empFrame.isMenuAppear);
//				label hóa đơn
			} else if (label == this.empFrame.lbOrder) {
				SwingUtilities.invokeLater(() -> {
					this.empFrame.changeToOrder();
				});

//				label thống kê
			} else if (label == this.empFrame.lblStatisticDay) {
				SwingUtilities.invokeLater(() -> {
					this.empFrame.changeToStatistic();
				});
				this.empFrame.isMenuAppear = !this.empFrame.isMenuAppear;
				this.empFrame.pnlMenu.setVisible(this.empFrame.isMenuAppear);

//				label thông tin cá nhân
			} else if (label == this.empFrame.lblInfo) {
				SwingUtilities.invokeLater(() -> {
					this.empFrame.changeToInfor();
				});
				this.empFrame.isMenuAppear = !this.empFrame.isMenuAppear;
				this.empFrame.pnlMenu.setVisible(this.empFrame.isMenuAppear);

//				label đăng nhâp
			} else if (label == this.empFrame.lblLogin) {
				this.empFrame.dispose();
				new loginFrame();
//				label đăng xuất
			} else if (label == this.empFrame.lblLogout) {
				this.empFrame.setEmployeeID(0);
				JOptionPane.showMessageDialog(this.empFrame, "Đăng xuất thành công!");
				this.empFrame.isMenuAppear = !this.empFrame.isMenuAppear;
				this.empFrame.pnlMenu.setVisible(this.empFrame.isMenuAppear);
			} else if(label==this.empFrame.lblAdd) {
				this.empFrame.changeToAdd();
				this.empFrame.isMenuAppear = !this.empFrame.isMenuAppear;
				this.empFrame.pnlMenu.setVisible(this.empFrame.isMenuAppear);
			} else if (label == this.empFrame.lblChangePage) {
	            this.empFrame.dispose();
	            SwingUtilities.invokeLater(() -> {
	            	new customerFrame(6);
				});
	            
	        }
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.empDAO.closeDatabase();
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

}
