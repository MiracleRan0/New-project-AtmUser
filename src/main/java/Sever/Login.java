/*
 * Created by JFormDesigner on Mon Jun 03 21:38:27 CST 2024
 */

package Sever;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * @author 29621
 */
public class Login extends JFrame {
    private int loginAttempts = 0; // 登录尝试次数
    private static final int MAX_ATTEMPTS = 3; // 最大尝试次数
    public String loggedInAccount;
    public static void main(String[] args) {
        Login login=new Login();
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Login() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        // 获取用户输入的账户和密码
        String account = textField1.getText();
        String password = new String(passwordField1.getPassword());

        // 连接到数据库并验证用户
        if (validateUser(account, password,"SELECT * FROM ATM_user WHERE user_account = ? AND user_password = ?","user_account" )) {
            JOptionPane.showMessageDialog(null, "登录成功！");
            Form form =new Form();
            form.setVisible(true);
            loggedInAccount = account;
            loginAttempts = 0; // 重置登录尝试次数
        } else {
            loginAttempts++; // 登录失败，增加尝试次数
            JOptionPane.showMessageDialog(null, "登录失败，请检查用户名和密码！");
            if (loginAttempts >= MAX_ATTEMPTS) {
                JOptionPane.showMessageDialog(null, "您已连续三次输入错误，系统将自动退出！");
                System.exit(0); // 达到最大尝试次数，退出系统
            }
        }
    }
    private void button2(ActionEvent e) {
        // TODO add your code here
        dispose();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d26\u53f7");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(95, 65), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(95, 95), label2.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(130, 60, 95, textField1.getPreferredSize().height);
        contentPane.add(passwordField1);
        passwordField1.setBounds(130, 95, 95, passwordField1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1);
        button1.setBounds(15, 175, 95, 30);

        //---- button2 ----
        button2.setText("\u9000\u51fa");
        button2.addActionListener(e -> button2(e));
        contentPane.add(button2);
        button2.setBounds(250, 175, 85, 30);

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
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public boolean validateUser(String account, String password, String query, String field) {
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true";
        String userName = "GHR22367106";
        String userPassword = "22367106";

        try (Connection conn = DriverManager.getConnection(dbURL, userName, userPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, account);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 检查用户状态
                String userStatus = rs.getString("user_status");
                if ("Locked".equalsIgnoreCase(userStatus)) {
                    JOptionPane.showMessageDialog(null, "登录失败，账户已被锁定！");
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库操作出错：" + e.getMessage());
            return false;
        }
    }

}
