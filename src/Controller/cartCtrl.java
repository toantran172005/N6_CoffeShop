package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DAO.CustomerDAOIMP;
import Frames.cartFrame;
import Frames.customerFrame;
import Models.CartItems;
import Models.Products;

public class cartCtrl implements ActionListener, ItemListener {
	public CustomerDAOIMP cusDAO;
	public cartFrame cart;
	public customerFrame cusFrame;
	public int count = 0;

	public cartCtrl(cartFrame cart, customerFrame cusFrame) {
		super();
		this.cart = cart;
		this.cusFrame = cusFrame;
		cusDAO = new CustomerDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj instanceof JCheckBox) {
			JCheckBox cb = (JCheckBox) obj;
//			 Tích chọn 1 sản phẩm
			if (this.cart.mapCheckBox.containsKey(cb)) {
				CartItems item = this.cart.mapCheckBox.get(cb);
				Products p = item.getProduct();
				JLabel quantity = this.cart.mapQuantity.get(p);
				String preTotal = this.cart.lblTotal.getText().replace("Tổng thanh toán: ₫", "").replace(",", "")
						.trim();

				double total = Double.valueOf(preTotal);
				double price = Double.valueOf(quantity.getText()) * p.getPrice();

				if (cb.isSelected())
					total += price;
				else
					total -= price;

				this.cart.lblTotal.setText("Tổng thanh toán: ₫" + String.format("%,.0f", total));
				this.cart.btnCheckout.setText("Mua hàng (" + this.count + ")");
//				 Tích chọn tất cả
			} else if (cb == this.cart.checkAll) {
				if (this.cart.checkAll.isSelected()) {
					for (JCheckBox cbox : this.cart.mapCheckBox.keySet()) {
						cbox.setSelected(true);
					}
					updateTotal();
				} else {
					for (JCheckBox cbox : this.cart.mapCheckBox.keySet()) {
						cbox.setSelected(false);
					}
					updateTotal();
				}
			}

		} else if (obj instanceof JButton) {
			JButton btn = (JButton) obj;
//			 Tăng số lượng
			if (this.cart.mapPlus.containsKey(btn)) {
				CartItems item = this.cart.mapPlus.get(btn);
				Products p = item.getProduct();
				JLabel quantity = this.cart.mapQuantity.get(p);
				int qty = Integer.parseInt(quantity.getText());
				qty += 1;
				quantity.setText(String.valueOf(qty));
				item.setQuantity(qty);
				this.cusDAO.updateCartItem(cusFrame.getCustomerID(), p, qty);
				updateTotal();
//				 Giảm số lượng
			} else if (this.cart.mapMinus.containsKey(btn)) {
				CartItems item = this.cart.mapMinus.get(btn);
				Products p = item.getProduct();
				JLabel quantity = this.cart.mapQuantity.get(p);
				int qty = Integer.parseInt(quantity.getText());
				if (qty > 1) {
					qty -= 1;
					quantity.setText(String.valueOf(qty));
					item.setQuantity(qty);
					this.cusDAO.updateCartItem(cusFrame.getCustomerID(), p, qty);
					updateTotal();
				}
//				Xóa sản phẩm
			} else if (this.cart.mapDelete.containsKey(btn)) {
				Products p = this.cart.mapDelete.get(btn);
				this.cusDAO.deleteCartItem(this.cusFrame.getCustomerID(), p);
				SwingUtilities.invokeLater(() -> {
					this.cusFrame.changToCart();
				});

//				Mua hàng
			} else if (btn == this.cart.btnCheckout) {
				ArrayList<CartItems> list = new ArrayList<>();
				for (Map.Entry<JCheckBox, CartItems> entry : this.cart.mapCheckBox.entrySet()) {
					JCheckBox cb = entry.getKey();
					CartItems item = entry.getValue();

					if (cb.isSelected()) {
						list.add(item);
					}
				}

				if (list.isEmpty())
					JOptionPane.showMessageDialog(this.cusFrame, "Vui lòng chọn sản phẩm để mua");
				else
					SwingUtilities.invokeLater(() -> {
						this.cusFrame.changToOrder(list);
		            });
					
			}
		}
	}

	public ArrayList<CartItems> getListItems() {
		ArrayList<CartItems> list = cusDAO.getCartItemsByCustomerID(this.cusFrame.getCustomerID());
		return list;
	}

	public void updateTotal() {
		double total = 0;
		int count = 0;
		for (Map.Entry<JCheckBox, CartItems> entry : this.cart.mapCheckBox.entrySet()) {
			JCheckBox cb = entry.getKey();
			CartItems item = entry.getValue();
			if (cb.isSelected()) {
				Products p = item.getProduct();
				int quantity = item.getQuantity();
				total += p.getPrice() * quantity;
				count++;
			}
		}
		this.cart.lblTotal.setText("Tổng thanh toán: ₫" + String.format("%,.0f", total));
		this.cart.btnCheckout.setText("Mua hàng (" + count + ")");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)
			count++;
		else
			count--;
	}
}