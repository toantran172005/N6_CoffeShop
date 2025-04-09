package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import DAO.CustomerDAOIMP;
import Frames.customerFrame;

public class customerCtrl implements ActionListener, MouseListener {
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

	        if (this.cusframe.mapPlus.containsKey(btn)) {
	            JLabel quantity = cusframe.mapPlus.get(btn);
	            int qty = Integer.parseInt(quantity.getText());
	            quantity.setText(String.valueOf(qty + 1));
	        } else if (cusframe.mapMinus.containsKey(btn)) {
	            JLabel quantity = cusframe.mapMinus.get(btn);
	            int qty = Integer.parseInt(quantity.getText());
	            if (qty > 1)
	            	quantity.setText(String.valueOf(qty - 1));
	        }
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
