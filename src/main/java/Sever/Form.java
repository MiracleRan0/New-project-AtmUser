/*
 * Created by JFormDesigner on Mon Jun 03 21:56:36 CST 2024
 */

package Sever;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 29621
 */
public class Form extends JFrame {
    public static void main(String[] args) {
      Form form=new Form();
      form.setVisible(true);
      form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Form() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        SearchBalance searchBalance=new SearchBalance();
        searchBalance.setVisible(true);
    }

    private void button4(ActionEvent e) {
        // TODO add your code here
        System.exit(0);
    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        saveMoney save =new saveMoney();
        save.setVisible(true);
    }

    private void button3(ActionEvent e) {
        // TODO add your code here
        getMoney get = new getMoney();
        get.setVisible(true);
    }

    private void button5(ActionEvent e) {
        // TODO add your code here
        lockedCard locked =new lockedCard();
        locked.setVisible(true);
    }

    private void button6(ActionEvent e) {
        // TODO add your code here
     System.exit(0);
    }

    private void button7(ActionEvent e) {
        // TODO add your code here
        changePassword change = new changePassword();
        change.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u4f59\u989d");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(10, 10), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5b58\u6b3e");
        button2.addActionListener(e -> button2(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(10, 110), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u53d6\u6b3e");
        button3.addActionListener(e -> button3(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(10, 225), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u9000\u5361");
        button4.addActionListener(e -> button4(e));
        contentPane.add(button4);
        button4.setBounds(370, 15, 65, button4.getPreferredSize().height);

        //---- button5 ----
        button5.setText("\u9501\u5361");
        button5.addActionListener(e -> button5(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(370, 120), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("\u5173\u95ed");
        button6.addActionListener(e -> button6(e));
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(375, 230), button6.getPreferredSize()));

        //---- button7 ----
        button7.setText("\u4fee\u6539\u5bc6\u7801");
        button7.addActionListener(e -> button7(e));
        contentPane.add(button7);
        button7.setBounds(new Rectangle(new Point(370, 300), button7.getPreferredSize()));

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
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
