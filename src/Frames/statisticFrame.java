package Frames;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import Controller.orderEmpCtrl;
import Controller.statisCtrl;
import Models.Orders;

public class statisticFrame {
	public orderEmpCtrl orCtrl;
	public Map<JButton, Orders> btnMap = new HashMap<>();
    public statisCtrl ctrl;
    public JButton btnBack;
    public JButton btnReport;
    public int orderTotal;
	public int stt;

    public statisticFrame(employeeFrame empFrame) {
    	this.orCtrl= new orderEmpCtrl(null, empFrame, this);
        this.ctrl = new statisCtrl(this, empFrame);
    }

	public JPanel getStatisticPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // JPanel chứa danh sách hóa đơn
        JPanel pnlItems = new JPanel();
        pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.Y_AXIS));
        pnlItems.setBackground(Color.WHITE);

        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        JLabel lblSTT = new JLabel("STT");
		lblSTT.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSTT.setPreferredSize(new Dimension(50, 30));

        JLabel lblOrderID = new JLabel("Mã hóa đơn");
        lblOrderID.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblOrderID.setPreferredSize(new Dimension(100, 30));

        JLabel lblOrderDate = new JLabel("Ngày lập");
        lblOrderDate.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblOrderDate.setPreferredSize(new Dimension(150, 30));

        JLabel lblEmployeeName = new JLabel("Tên nhân viên");
        lblEmployeeName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblEmployeeName.setPreferredSize(new Dimension(150, 30));

        JLabel lblTotal = new JLabel("Thành tiền");
        lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTotal.setPreferredSize(new Dimension(150, 30));

        JLabel lblNote = new JLabel("Ghi chú");
        lblNote.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNote.setPreferredSize(new Dimension(150, 30));
        
        headerPanel.add(lblSTT);
		headerPanel.add(Box.createHorizontalStrut(10));
        headerPanel.add(lblOrderID);
        headerPanel.add(Box.createHorizontalStrut(10));
        headerPanel.add(lblOrderDate);
        headerPanel.add(Box.createHorizontalStrut(10));
        headerPanel.add(lblEmployeeName);
        headerPanel.add(Box.createHorizontalStrut(10));
        headerPanel.add(lblTotal);
        headerPanel.add(Box.createHorizontalStrut(10));
        headerPanel.add(lblNote);

        pnlItems.add(headerPanel);

        // Danh sách hóa đơn
        stt = 1;
        orderTotal = 0;
        for (Orders order : ctrl.getOrders()) {
            int orderID = order.getOrderID();
            String employeeName = ctrl.getEmployeeName(order.getEmployeeID().getEmployeeID());
            double price = order.getTotalPrice();
            orderTotal += price;

            // Tạo panel cho mỗi hóa đơn
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            
            JLabel lblItemSTT = new JLabel(String.valueOf(stt++));
			lblItemSTT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			lblItemSTT.setPreferredSize(new Dimension(50, 30));

            JLabel lblItemOrderID = new JLabel(String.valueOf(orderID));
            lblItemOrderID.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            lblItemOrderID.setPreferredSize(new Dimension(100, 30));

            String date = order.getOrderDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            JLabel lblItemOrderDate = new JLabel(date);
            lblItemOrderDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            lblItemOrderDate.setPreferredSize(new Dimension(150, 30));

            JLabel lblItemEmployeeName = new JLabel(employeeName);
            lblItemEmployeeName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            lblItemEmployeeName.setPreferredSize(new Dimension(150, 30));
            
            JLabel lblItemPrice = new JLabel(String.format("₫%,d", (int) price));
            lblItemPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            lblItemPrice.setPreferredSize(new Dimension(150, 30));

            JButton btnDetail = new JButton("Chi tiết");
            btnDetail.setFont(new Font("Times New Roman", Font.BOLD, 18));
            btnDetail.setForeground(Color.WHITE);
            btnDetail.setBorderPainted(false);
            btnDetail.setBackground(new Color(0, 102, 102));
            btnMap.put(btnDetail, order);
            btnDetail.addActionListener(orCtrl);
            btnDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            itemPanel.add(lblItemSTT);
			itemPanel.add(Box.createHorizontalStrut(10));
            itemPanel.add(lblItemOrderID);
            itemPanel.add(Box.createHorizontalStrut(10));
            itemPanel.add(lblItemOrderDate);
            itemPanel.add(Box.createHorizontalStrut(10));
            itemPanel.add(lblItemEmployeeName);
            itemPanel.add(Box.createHorizontalStrut(10));
            itemPanel.add(lblItemPrice);
            itemPanel.add(Box.createHorizontalStrut(300));
            itemPanel.add(btnDetail);

            pnlItems.add(itemPanel);
        }
        
//      Tổng hóa đơn
		JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		totalPanel.setBackground(Color.WHITE);
		totalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

		JLabel lblorderTotalLabel = new JLabel("Tổng doanh thu: ");
		lblorderTotalLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		JLabel lblorderTotal = new JLabel(String.format("₫%,d", (int) orderTotal));
		lblorderTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblorderTotal.setForeground(Color.RED);

		totalPanel.add(lblorderTotalLabel);
		totalPanel.add(lblorderTotal);

//      Thêm danh sách sản phẩm và tổng hóa đơn vào mainPanel
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(pnlItems, BorderLayout.CENTER);
		contentPanel.add(totalPanel, BorderLayout.SOUTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footer.setPreferredSize(new Dimension(0, 60));
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        btnBack = new JButton("Quay lại");
        btnBack.setPreferredSize(new Dimension(150, 40));
        btnBack.setBackground(new Color(200, 200, 200));
        btnBack.setBorderPainted(false);

        btnReport = new JButton("Báo cáo thống kê");
        btnReport.setPreferredSize(new Dimension(200, 40));
        btnReport.setBackground(new Color(50, 150, 50));
        btnReport.setForeground(Color.WHITE);
        btnReport.setBorderPainted(false);

        for (JButton btn : new JButton[] { btnBack, btnReport }) {
            btn.addActionListener(ctrl);
            btn.setFont(new Font("Times New Roman", Font.BOLD, 18));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        footer.add(btnBack);
        footer.add(btnReport);

        mainPanel.add(footer, BorderLayout.SOUTH);

        return mainPanel;
    }
	
    public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	public int getStt() {
		return stt;
	}


	public void setStt(int stt) {
		this.stt = stt;
	}

}