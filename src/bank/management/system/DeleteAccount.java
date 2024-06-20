/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class DeleteAccount extends JFrame implements ActionListener{
    
    private long account_number;
      JLabel del_acc_no_lbl;
    JTextField del_acc_no_fld;
    JButton back;
    JButton delete;

  
       public DeleteAccount() {
 //          this.account_number = account_number;
           setTitle("BANK MANAGEMENT SYSTEM");
           setSize(1540,820);

             JPanel panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
         ImageIcon imageIcon = new ImageIcon("./img9.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel1.setLayout(null);
                setContentPane(panel1);


        
        
        
         ImageIcon imageIcon = new ImageIcon("./logo2.jpg"); // Specify the path to your image file

        // Create a label to hold the image
        JLabel label = new JLabel();
        label.setIcon(imageIcon);
        label.setBounds(475,25,150,95);
        add(label);
        //bank label
        JLabel label1=new JLabel("HAPN BANK");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 40));
       label1.setBackground(Color.LIGHT_GRAY);
     //  label1.setForeground(Color.DARK_GRAY);
 //      label1.setOpaque(true);
        label1.setBounds(640,50,250,70);
        add(label1);

  

        del_acc_no_lbl= new JLabel("ENTER ACCOUNT NUMBER TO BE DELETED");
        del_acc_no_lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        del_acc_no_lbl.setBounds(300,299,400,50);
        add(del_acc_no_lbl);

        del_acc_no_fld = new JTextField();
        del_acc_no_fld.setFont(new Font("Times New Roman", Font.BOLD, 25));
        del_acc_no_fld.setBounds(300,370,330,30);
        add(del_acc_no_fld);

        delete = new JButton("DELETE");
        delete.setBounds(490,420,150,35);
        add(delete);
        delete.addActionListener(this);

        back = new JButton("BACK");
        back.setBounds(490,470,150,35);
        add(back);
        back.addActionListener(this);
        

        setLayout(null);
        setVisible(true);
    
    }
    
     

    @Override
    public void actionPerformed(ActionEvent e) {
           try {
               if (e.getSource() == back) {
                   setVisible(false);
                   new AdminPage().setVisible(true);
                   dispose();

               } else if (e.getSource() == delete) {
                   String s_acno = del_acc_no_fld.getText();
                   if (s_acno.equals("")) {
                       JOptionPane.showMessageDialog(null, "Please enter the Account Number");
                   } else {
                       long account_number = Long.parseLong(s_acno);
                       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
                       connection.createStatement().executeUpdate("delete from transactionstatement where account_number = " + account_number);
                       connection.createStatement().executeUpdate("delete from user_balance where account_number  = " + account_number);
                       connection.createStatement().executeUpdate("delete from user_login where account_number  = " + account_number);
                       connection.createStatement().executeUpdate("delete from kyc where account_number  = " + account_number);
                       connection.createStatement().executeUpdate("delete from transactionstatement_interest where account_number  = " + account_number);
                       JOptionPane.showMessageDialog(null, "Account  Number " + account_number + " data deleted");
                       setVisible(false);
                       new AdminPage().setVisible(true);
                       dispose();
                   }
               }
           }
                
           catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
    }
}
    

