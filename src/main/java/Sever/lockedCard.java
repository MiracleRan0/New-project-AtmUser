/*
 * Created by JFormDesigner on Tue Jun 04 09:16:23 CST 2024
 */

package Sever;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * @author 29621
 */
public class lockedCard extends JFrame {
    public static void main(String[] args) {
        lockedCard locked=new lockedCard();
        locked.setVisible(true);
        locked.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public lockedCard() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        locked();
    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        accountTxt = new JTextField();
        passTxt = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d26\u6237");
        contentPane.add(label1);
        label1.setBounds(75, 25, 45, label1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(75, 50, 40, label2.getPreferredSize().height);
        contentPane.add(accountTxt);
        accountTxt.setBounds(110, 20, 95, accountTxt.getPreferredSize().height);
        contentPane.add(passTxt);
        passTxt.setBounds(110, 50, 95, passTxt.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u9501\u5361");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(40, 200), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u9000\u51fa");
        button2.addActionListener(e -> button2(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(285, 200), button2.getPreferredSize()));

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
    private JLabel label2;
    private JTextField accountTxt;
    private JTextField passTxt;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private void locked() {
        String account = this.accountTxt.getText();
        String password = this.passTxt.getText();
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true";
        String userName = "GHR22367106";
        String userPassword = "22367106";
        String selectSQL = "SELECT user_password FROM ATM_user WHERE user_account = ? ";
        String updateSQL = "UPDATE ATM_user SET user_status = 'Locked' WHERE user_account = ? AND user_password = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, userName, userPassword);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL);
             PreparedStatement selectpstmt = conn.prepareStatement(selectSQL)) {

            // 设置查询密码的参数
            selectpstmt.setString(1, account);
            ResultSet rs = selectpstmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("user_password");
                // 检查输入的密码是否与数据库中的密码匹配
                if (password.equals(dbPassword)) {
                    // 设置更新状态的参数
                    pstmt.setString(1, account);
                    pstmt.setString(2, password);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "锁卡成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "锁卡失败，账户状态未改变！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "锁卡失败，密码不正确！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "锁卡失败，账户不存在！");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库操作出错：" + e.getMessage());
        }

    }
}

