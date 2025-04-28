
package Controller;

import java.awt.event.*;
import javax.swing.*;
import DAO.orderDAOIMP;
import Frames.employeeFrame;
import Frames.orderEmpFrame;
import Frames.statisticFrame;
import Models.Orders;

public class orderEmpCtrl implements ActionListener {
    public orderEmpFrame ordFrame;
    public employeeFrame empFrame;
    public orderDAOIMP ordDao;
	public statisticFrame staFrame;

    public orderEmpCtrl(orderEmpFrame ordFrame, employeeFrame empFrame, statisticFrame staFrame) {
    	this.staFrame= staFrame;
        this.ordFrame = ordFrame;
        this.empFrame = empFrame;
        this.ordDao = new orderDAOIMP();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton btn) {

            if (ordFrame != null && ordFrame.btnMap.containsKey(btn)) {
                Orders o = ordFrame.btnMap.get(btn);
                SwingUtilities.invokeLater(() -> {
                	empFrame.changeToDetailOrder(o);
	            });
                
            }else if (staFrame != null && staFrame.btnMap.containsKey(btn)) {
                Orders o = staFrame.btnMap.get(btn);
                SwingUtilities.invokeLater(() -> {
                	empFrame.changeToDetailOrderStatics(o);
	            });
                
            }
        }
    }
}
