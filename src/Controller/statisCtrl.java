package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DAO.EmployeeDAOIMP;
import DAO.orderDAOIMP;
import Frames.employeeFrame;
import Frames.statisticFrame;
import Models.Employees;
import Models.Orders;

public class statisCtrl implements ActionListener {
	public statisticFrame staFrame;
	public employeeFrame empFrame;
	public orderDAOIMP ordDAO;
	public EmployeeDAOIMP empDAO;
	
	public statisCtrl(statisticFrame staFrame, employeeFrame empFrame) {
		super();
		this.staFrame = staFrame;
		this.empFrame = empFrame;
		ordDAO = new orderDAOIMP();
		empDAO = new EmployeeDAOIMP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj instanceof JButton) {
			
			JButton btn = (JButton) obj;
			
			if(btn == this.staFrame.btnBack) {
				SwingUtilities.invokeLater(() -> {
					this.empFrame.updateProductPanel(empFrame.getListProduct());
	            });
			} else if(btn == this.staFrame.btnReport) {
				int choice = JOptionPane.showConfirmDialog(
					    this.empFrame,
					    "Xác nhận báo cáo thống kê?",
					    "Thông báo",
					    JOptionPane.YES_NO_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					if(this.ordDAO.createStatistic(this.empFrame.getEmployeeID(),this.staFrame.getOrderTotal(),this.staFrame.getStt()-1)) {
						JOptionPane.showMessageDialog(this.empFrame, "Đã lưu file thống kê!");
					} else JOptionPane.showMessageDialog(this.empFrame, "File thống kê đã được tạo!");
					
				}
			}
			
		}
		
	}

	public ArrayList<Orders> getOrders() {
		return ordDAO.getAllOrderToday();
	}

	public String getEmployeeName(int employeeID) {
		Employees emp = empDAO.getEmployee(employeeID);
		return emp.getEmployeeName();
	}
	

}
