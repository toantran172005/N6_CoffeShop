package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.orderEmpCtrl;
import DAO.orderDAOIMP;
import Models.Employees;
import Models.Orders;


public class orderEmpFrame {
    public Employees employees;
    public orderEmpCtrl orCtrl;
    public int employeeID;
    public employeeFrame empFrame;
    public Map<JButton, Orders> btnMap = new HashMap<>();
    @SuppressWarnings("deprecation")
	NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
    public orderEmpFrame(int employeeID, employeeFrame empFrame) {
        this.employeeID = employeeID;
        this.orCtrl = new orderEmpCtrl(this, empFrame); // Khởi tạo controller trước
    }

    public JPanel loadOrder(int employeeID) {
        JPanel pMain = new JPanel();
        pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
        pMain.setBorder(new EmptyBorder(15, 15, 15, 15));
        pMain.setBackground(new Color(240, 240, 240));

        orderDAOIMP dao = new orderDAOIMP();
        List<Orders> list = dao.getUnprocessedOrdersByEmp(employeeID);

        Font font = new Font("Times New Roman", Font.BOLD, 30);

        if (list.isEmpty()) {
            JLabel noDataLabel = new JLabel("Không có hóa đơn nào cần xử lý.");
            noDataLabel.setFont(font);
            noDataLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            pMain.add(noDataLabel);
        } else {
            for (Orders o : list) {
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.LINE_AXIS));
                card.setBackground(Color.WHITE);
                card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                card.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                card.setOpaque(true);
                card.setBorder(new EmptyBorder(10, 15, 10, 15));
                String formattedPrice = currencyFormat.format(o.getTotalPrice()) + " VNĐ";
                JLabel lblID = new JLabel("Mã HĐ: " + o.getOrderID());
                JLabel lblDate = new JLabel("Ngày: " + o.getOrderDate());
                JLabel lblTotal = new JLabel("Tổng: " + formattedPrice);
                JLabel lblState = new JLabel("Trạng thái: " + o.getState());
                
                for(JLabel label : new JLabel[]{lblID, lblDate, lblTotal, lblState}) {
                	label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                }

                JButton btnDetail = new JButton("Chi tiết");
                btnDetail.setFont(new Font("Times New Roman", Font.BOLD, 23));
                btnDetail.setForeground(Color.WHITE);
                btnDetail.setBorderPainted(false);
                btnDetail.setBackground(new Color(0, 102, 102));
                btnMap.put(btnDetail, o);
                btnDetail.addActionListener(orCtrl);
                btnDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                card.add(lblID);
                card.add(Box.createHorizontalStrut(25));
                card.add(lblDate);
                card.add(Box.createHorizontalStrut(25));
                card.add(lblTotal);
                card.add(Box.createHorizontalStrut(25));
                card.add(lblState);
                card.add(Box.createHorizontalGlue()); // đẩy nút về phải
                card.add(btnDetail);

                pMain.add(card);
                pMain.add(Box.createVerticalStrut(10));
            }
        }

        JScrollPane scrollPane = new JScrollPane(pMain);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);

        JPanel pnlContent = new JPanel(new BorderLayout());
        pnlContent.add(scrollPane, BorderLayout.CENTER);

        return pnlContent;
    }
}
