package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class balance_enquiry extends JFrame implements ActionListener {
    JButton back;
    JLabel bal;
    static long acc_no;
    long account_number;
    double balance;

    balance_enquiry(long account_number) {
        this.account_number = account_number;
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

        
          JLabel     acno = new JLabel();
            acno.setFont(new Font("Times New Roman", Font.BOLD, 40));
            acno.setBounds(190, 150, 700, 35);
            acno.setText("Account Number : "+account_number);
            add(acno);

        bal = new JLabel();
        bal.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bal.setBounds(190, 350, 810, 35);
        add(bal);

        back = new JButton("BACK");
        back.setBounds(390, 633, 150, 35);
        add(back);
        back.addActionListener(this);
        this.showbalance();
        setLayout(null);
        setVisible(true);
    }
    public void showbalance(){
        try{
            double d_balance;
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");

            ResultSet rs1 = connection.createStatement().executeQuery("select * from user_balance where account_number = '"+account_number+"'");
            if(rs1.next()) {
                d_balance = rs1.getDouble("balance");
            }else{
                d_balance=0;
            }
            System.out.println(d_balance);
            balance=d_balance;
                    bal.setText("Your Current Account Balance is Rs "+balance);

           
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try{
            
            if (ae.getSource()==back){
                setVisible(false);
                new transaction(account_number).setVisible(true);
            }
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new balance_enquiry(acc_no);
    }


}
