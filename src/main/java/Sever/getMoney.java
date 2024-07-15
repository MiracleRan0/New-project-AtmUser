/*
 * Created by JFormDesigner on Mon Jun 03 22:28:17 CST 2024
 */

package Sever;

import Modale.User;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.*;

/**
 * @author 29621
 */
public class getMoney extends JFrame {




    public static void main(String[] args) {
        getMoney get=new getMoney();
        get.setVisible(true);
        get.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public getMoney() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        GetMoney();
    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        upDate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        getTxt = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u53d6\u6b3e\u91d1\u989d");
        contentPane.add(label1);
        label1.setBounds(10, 5, 55, 15);
        contentPane.add(getTxt);
        getTxt.setBounds(75, 5, 95, getTxt.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 30, 395, 120);

        //---- button1 ----
        button1.setText("\u53d6\u6b3e");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(5, 215), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5237\u65b0");
        button2.addActionListener(e -> button2(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(280, 215), button2.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField getTxt;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private void GetMoney() {
        String balance = this.getTxt.getText();
        BigDecimal atmAmount = BigDecimal.ZERO;
        BigDecimal withdrawalAmount = new BigDecimal(balance); // 将字符串转换为BigDecimal
        BigDecimal withdrawalUp = new BigDecimal(80000);
        BigDecimal withdrawalDown = new BigDecimal(0);
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true"; // 假设端口是默认的1433
        String userName = "GHR22367106"; // 替换为您的数据库用户名
        String userPassword = "22367106"; // 替换为您的密码
        String SQL = "SELECT atmAmount FROM ATM_user WHERE user_id = ? ";
        try (Connection conn = DriverManager.getConnection(dbURL, userName, userPassword);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // 设置PreparedStatement参数
            pstmt.setString(1, "22367106"); // 替换为实际的ATM机ID
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                atmAmount = rs.getBigDecimal("atmAmount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "操作出错：" + e.getMessage());
        }
        // 将int转换为BigDecimal
        if (withdrawalAmount.compareTo(atmAmount) > 0) {
            if (withdrawalAmount.compareTo(withdrawalUp) <= 0 && withdrawalAmount.compareTo(withdrawalDown) >= 0) {
                String updateSQL = "UPDATE User_information SET balance = balance - ?";
                try (Connection conn = DriverManager.getConnection(dbURL, userName, userPassword);
                     PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                    // 设置PreparedStatement参数
                    pstmt.setString(1, balance);
                    // 执行插入操作
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "取款成功！");
                    } else {
                        JOptionPane.showMessageDialog(this, "取款失败！");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "操作出错：" + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "取款失败！,超出最大取款额");
            }
            String updateSQL = "UPDATE ATM_user SET atmAmount = atmAmount - ?";
            try (Connection conn = DriverManager.getConnection(dbURL, userName, userPassword);
                 PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                // 设置PreparedStatement参数
                pstmt.setString(1, balance);
                // 执行插入操作
                int rowsAffected = pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
                JOptionPane.showMessageDialog(this, "取款失败！,超出最大取款额");
            }
        }

    private void upDate() {
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true"; // 假设端口是默认的1433
        String userName = "GHR22367106"; // 替换为您的数据库用户名
        String userPassword = "22367106"; // 替换为您的密码
        // 从数据库中查询数据并填充到表格模型中
        try (Connection connection = DriverManager.getConnection(dbURL, userName, userPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT user_name,balance FROM User_information")) {

            // 获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            DefaultTableModel model = new DefaultTableModel();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }
            // 将查询结果填充到 DefaultTableModel
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
            table1.setModel(model);
            for (int i = 0; i < columnCount; i++) {
                TableColumn column = table1.getColumnModel().getColumn(i);
                column.setHeaderValue(metaData.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
