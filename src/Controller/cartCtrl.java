	package Controller;
	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	
	import DAO.CustomerDAOIMP;
	import Frames.cartFrame;
	import Frames.customerFrame;
	import Models.CartItems;
	
	public class cartCtrl implements ActionListener {
		public CustomerDAOIMP cusDAO;
		public cartFrame cart;
		public customerFrame cusFrame;
		
		public cartCtrl(cartFrame cart, customerFrame cusFrame) {
			super();
			this.cart = cart;
			this.cusFrame = cusFrame;
			cusDAO = new CustomerDAOIMP();
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			
		}
		
		public ArrayList<CartItems> getListItems(){
			ArrayList<CartItems> list = cusDAO.getCartItemsByCustomerID(this.cusFrame.getCustomerID());
			return list;
		}
	
		
		
		
	}
