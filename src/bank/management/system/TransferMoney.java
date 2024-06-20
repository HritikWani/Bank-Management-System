/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TransferMoney  extends JFrame implements ActionListener{
    private long account_number;
    JLabel lbl_to_acno, lbl_amount;
    JTextField fld_to_acno,fld_amount;
    JButton back,send;
    
    
    
    public TransferMoney() {
        
    }
    
     public TransferMoney(long account_number)  {
         this.account_number = account_number;
         setTitle("BANK MANAGEMENT SYSTEM");
        setSize(1540,820);
        setLayout (null);

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

        
            JLabel     acno = new JLabel();
            acno.setFont(new Font("Times New Roman", Font.BOLD, 40));
            acno.setBounds(300, 150, 700, 35);
            acno.setText("Account Number : "+account_number);
            add(acno);
            
            lbl_to_acno= new JLabel("PLEASE ENTER ACCOUNT NUMBER TO SEND");
        lbl_to_acno.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl_to_acno.setBounds(300,250,500,50);
        add(lbl_to_acno);

        fld_to_acno = new JTextField();
        fld_to_acno.setFont(new Font("Times New Roman", Font.BOLD, 25));
        fld_to_acno.setBounds(820,260,330,30);
        add(fld_to_acno);
        
           lbl_amount= new JLabel("PLEASE ENTER AMOUNT");
        lbl_amount.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl_amount.setBounds(300,350,500,50);
        add(lbl_amount);
        
        
        fld_amount = new JTextField();
        fld_amount.setFont(new Font("Times New Roman", Font.BOLD, 25));
        fld_amount.setBounds(820,360,330,30);
        add(fld_amount);
        
        send= new JButton("SEND");
        send.setBounds(650,500,150,35);
        add(send);
        send.addActionListener(this);

        back = new JButton("BACK");
        back.setBounds(450,500,150,35);
        add(back);
        back.addActionListener(this);
        
        setVisible (true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s_to_acno = fld_to_acno.getText();
        String s_amount = fld_amount.getText();
        long to_acno = 0;
        double amount = 0;
        
        if(e.getSource()==send){
            if(s_to_acno.equals("")){
                                    JOptionPane.showMessageDialog(null,"Enter Account Number to send money");

            }else if  (s_amount.equals("")){
                                   JOptionPane.showMessageDialog(null,"Enter Amount ");
            }else {
                Connection connection = null;
                try{
                    to_acno = Long.parseLong(s_to_acno);
                    amount = Double.parseDouble(s_amount);
                           
                 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root"); 
                    connection.setAutoCommit(false);
                    // withdraw from from account
                     ResultSet rs1 = connection.createStatement().executeQuery("select*from user_balance where account_number = '"+account_number+"'");
                    double from_balance = 0;
                      if(rs1.next())
                      {
                             from_balance= rs1.getDouble("balance");
                      }

                    if(from_balance < amount){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }else{
                         connection.createStatement().executeUpdate("update user_balance set balance='"+ (from_balance-amount)+"' where account_number='"+account_number+"';");
                        ResultSet rs2 = connection.createStatement().executeQuery("select*from user_balance where account_number = '"+account_number+"'");
                       if(rs2.next())
                      {
                       from_balance=     rs2.getDouble("balance");
                      }
                       
                       String trquery1 = "insert into transactionstatement (tr_date_time,tr_amount,tr_type,account_number, final_balance) values " 
                               +"(?,?,?,?,?)";
                       PreparedStatement pst = connection.prepareStatement(trquery1);
                       
                       Date today = new Date();
                       Timestamp tr_date_time = new Timestamp(today.getTime());
                       pst.setTimestamp(1, tr_date_time);
                       pst.setDouble(2, amount);
                       pst.setString(3,"Debit");
                       pst.setLong(4, account_number);
                       pst.setDouble(5, from_balance);
                       int i = pst.executeUpdate();
                       System.out.println("transaction inserted "+i);
                        String trquery10 = "insert into transactionstatement_interest (tr_date_time,tr_amount,tr_type,account_number, final_balance) values "
                                +"(?,?,?,?,?)";
                        PreparedStatement pst10 = connection.prepareStatement(trquery10);
                        pst10.setTimestamp(1, tr_date_time);
                        pst10.setDouble(2, amount);
                        pst10.setString(3,"Debit");
                        pst10.setLong(4, account_number);
                        pst10.setDouble(5, from_balance);
                        pst10.executeUpdate();

                       // Deposite to account
                       double to_balance=0;
                    ResultSet rs3=connection.createStatement().executeQuery("select*from user_balance where account_number='"+to_acno+"';");
                    if(rs3.next()) {
                        to_balance=rs3.getDouble("balance");
                    }
                    double total_balance=to_balance+amount;
                    connection.createStatement().executeUpdate("update user_balance set balance='"+total_balance+"' where account_number='"+to_acno+"';");
                    ResultSet rs4=connection.createStatement().executeQuery("select*from user_balance where account_number='"+to_acno+"';");
                    if(rs4.next()) {
                        to_balance=rs4.getDouble("balance");
                    }else{
                        throw new RuntimeException("Account Does not Exist. Please enter valid acoound number");
                    }
                        System.out.println(to_balance);
                      String trquery2 = "insert into transactionstatement (tr_date_time,tr_amount,tr_type,account_number, final_balance) values " 
                               +"(?,?,?,?,?)";
                       PreparedStatement pst1 = connection.prepareStatement(trquery2);
                       
                       Date today1= new Date();
                       Timestamp tr_date_time1 = new Timestamp(today1.getTime());
                       pst1.setTimestamp(1, tr_date_time1);
                       pst1.setDouble(2, amount);
                       pst1.setString(3,"Credit");
                       pst1.setLong(4, to_acno);
                       pst1.setDouble(5, to_balance);
                       int i1 = pst1.executeUpdate();
                       System.out.println("transaction inserted "+i1);
                        String trquery20 = "insert into transactionstatement_interest (tr_date_time,tr_amount,tr_type,account_number, final_balance) values "
                                +"(?,?,?,?,?)";
                        PreparedStatement pst20 = connection.prepareStatement(trquery20);
                        pst20.setTimestamp(1, tr_date_time);
                        pst20.setDouble(2, amount);
                        pst20.setString(3,"Credit");
                        pst20.setLong(4, to_acno);
                        pst20.setDouble(5, to_balance);
                        pst20.executeUpdate();

                    connection.commit();
                     JOptionPane.showMessageDialog(null, "Rs. "+amount+" Credited Successfully to Benificiary Account :" + to_acno);
                      setVisible(false);
                     new transaction(account_number).setVisible(true);
                     dispose();
                    }
                }catch(Exception e1){
                    if(connection!= null){
                        try {
                            connection.rollback();
                            connection.setAutoCommit(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(TransferMoney.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                                                       JOptionPane.showMessageDialog(null,e1.getMessage());

                }
            }
           
            
        }else if(e.getSource()==back){
             setVisible(false);
                new transaction(account_number).setVisible(true);
                dispose();
        }
    }
    
    public static void main(String [] args){
        new TransferMoney (1234);
    }

    
    
}
