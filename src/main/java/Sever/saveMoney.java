/*
 * Created by JFormDesigner on Mon Jun 03 22:28:08 CST 2024
 */

package Sever;

import Modale.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * @author 29621
 */
public class saveMoney extends JFrame {
    public static void main(String[] args) {
        saveMoney save=new saveMoney();
        save.setVisible(true);
        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public saveMoney() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        saveMoney();
    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        upDate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        saveTxt = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5b58\u6b3e\u91d1\u989d");
        contentPane.add(label1);
        label1.setBounds(15, 5, 60, label1.getPreferredSize().height);
        contentPane.add(saveTxt);
        saveTxt.setBounds(70, 0, 95, saveTxt.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 20, 395, 165);

        //---- button1 ----
        button1.setText("\u5b58\u6b3e");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1);
        button1.setBounds(15, 205, 65, 25);

        //---- button2 ----
        button2.setText("\u5237\u65b0");
        button2.addActionListener(e -> button2(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(290, 210), button2.getPreferredSize()));

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
    private JTextField saveTxt;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private void saveMoney(){
        String balance =this.saveTxt.getText();
        User user =new User(balance);
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true"; // 假设端口是默认的1433
        String userName = "GHR22367106"; // 替换为您的数据库用户名
        String userPassword = "22367106"; // 替换为您的密码
        String updateSQL = "UPDATE ATM_user SET balance = balance + ?";
        try (Connection conn = DriverManager.getConnection(dbURL, userName, userPassword);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            // 设置PreparedStatement参数
            pstmt.setString(1, balance);
            // 执行插入操作
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "存款成功！");
            } else {
                JOptionPane.showMessageDialog(this, "存款失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "操作出错：" + e.getMessage());
        }
    }
    private void upDate() {
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true"; // 假设端口是默认的1433
        String userName = "GHR22367106"; // 替换为您的数据库用户名
        String userPassword = "22367106"; // 替换为您的密码
        // 从数据库中查询数据并填充到表格模型中
        try (Connection connection = DriverManager.getConnection(dbURL, userName, userPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT user_name,balance FROM ATM_user")) {

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
