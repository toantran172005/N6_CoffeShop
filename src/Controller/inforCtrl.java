package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DAO.CustomerDAOIMP;
import Frames.customerFrame;
import Frames.inforFrame;

public class inforCtrl implements ActionListener {
	private CustomerDAOIMP cusDAO;
	private inforFrame infor;
	private customerFrame cusFrame;
	
	
	public inforCtrl(inforFrame infor, customerFrame cusFrame) {
		super();
		this.infor = infor;
		this.cusFrame = cusFrame;
		cusDAO = new CustomerDAOIMP();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj instanceof JButton) {
			
			if(obj == this.infor.btnChange) {
				
			} else if (obj == this.infor.btnBack) {
				this.cusFrame.updateProductPanel(this.cusFrame.getListProduct()); 
			} else if (obj == this.infor.btnSave) {
				
			}
			
		}
		
	}

}
