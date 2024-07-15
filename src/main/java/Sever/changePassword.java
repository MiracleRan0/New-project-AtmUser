/*
 * Created by JFormDesigner on Tue Jun 04 08:30:41 CST 2024
 */

package Sever;

import Modale.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * @author 29621
 */
public class changePassword extends JFrame {
    public static void main(String[] args) {
        changePassword change =new changePassword();
        change.setVisible(true);
        change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public changePassword() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        ChangePassword();
    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        againTxt = new JTextField();
        changeTxt = new JTextField();
        oldTxt = new JTextField();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u65e7\u5bc6\u7801");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(75, 20), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u66f4\u6539\u540e\u7684\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(40, 60), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u4e8c\u6b21\u786e\u8ba4");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(65, 95), label3.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u4fee\u6539\u5bc6\u7801");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1);
        button1.setBounds(10, 185, 95, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("\u9000\u51fa");
        button2.addActionListener(e -> button2(e));
        contentPane.add(button2);
        button2.setBounds(225, 185, 95, button2.getPreferredSize().height);
        contentPane.add(againTxt);
        againTxt.setBounds(125, 95, 125, againTxt.getPreferredSize().height);
        contentPane.add(changeTxt);
        changeTxt.setBounds(125, 60, 125, 19);
        contentPane.add(oldTxt);
        oldTxt.setBounds(125, 20, 125, 19);

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
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    private JTextField againTxt;
    private JTextField changeTxt;
    private JTextField oldTxt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private void ChangePassword(){
        String old = this.oldTxt.getText();
        String change = this.changeTxt.getText();
        String again =this.changeTxt.getText();
        if (!change.equals(again)) {
            JOptionPane.showMessageDialog(null, "两次输入的新密码不一致！");
            return;
        }
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true"; // 假设端口是默认的1433
        String userName = "GHR22367106"; // 替换为您的数据库用户名
        String userPassword = "22367106"; // 替换为您的密码
        String selectSQL="SELECT user_password FROM ATM_user WHERE user_account = ? ";
        String updateSQL = "UPDATE ATM_user SET user_password = ? WHERE user_account = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, userName, userPassword);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL);
             PreparedStatement selectpstmt = conn.prepareStatement(selectSQL)) {
            // 设置PreparedStatement参数
            selectpstmt.setString(1, "22367106");
            ResultSet rs = selectpstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("user_password");

                // 验证旧密码
                if (old.equals(storedPassword)) {
                    // 旧密码正确，更新新密码
                    pstmt.setString(1, change);
                    pstmt.setString(2, "22367106");
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "密码修改成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "密码修改失败！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "旧密码输入错误！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户不存在！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "操作出错：" + e.getMessage());
        }
    }
}
