package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.Timestamp;

public class withdraw extends JFrame implements ActionListener {
    JLabel acno,amount,limit;
    JTextField withdraw_amount;
    JButton with,back;
    static long acc_no;
    long account_number;
    public withdraw(long account_number) {
        this.account_number=account_number;
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

        
        acno = new JLabel();
        acno.setFont(new Font("Times New Roman", Font.BOLD, 40));
        acno.setBounds(300, 150, 700, 35);
        acno.setText("Account Number : "+account_number);
        add(acno);

        limit = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        limit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        limit.setBounds(300,200,700,100);
        add(limit);

        amount= new JLabel("PLEASE ENTER YOUR AMOUNT");
        amount.setFont(new Font("Times New Roman", Font.BOLD, 20));
        amount.setBounds(300,299,400,50);
        add(amount);

        withdraw_amount = new JTextField();
        withdraw_amount.setFont(new Font("Times New Roman", Font.BOLD, 25));
        withdraw_amount.setBounds(300,370,330,30);
        add(withdraw_amount);

        with = new JButton("WITHDRAW");
        with.setBounds(490,420,150,35);
        add(with);
        with.addActionListener(this);

        back = new JButton("BACK");
        back.setBounds(490,470,150,35);
        add(back);
        back.addActionListener(this);

        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        try{
            String amount = withdraw_amount.getText();

            if(e.getSource()==with){
                if(amount.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
//                }else if (!amount.matches("[0-9]+")) {
//                    JOptionPane.showMessageDialog(null,"amount can be numeric only");
                } else if (Double.parseDouble(amount)>10000) {
                    JOptionPane.showMessageDialog(null, "Maximum amount limit is 10000");
                }

                else{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");

                    ResultSet rs1 = connection.createStatement().executeQuery("select*from user_balance where account_number = '"+account_number+"'");
                    double balance = 0;
                      if(rs1.next())
                      {
                       balance=     rs1.getDouble("balance");
                      }

                    if(balance < Double.parseDouble(amount)){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }else{
                        double withdrawamount=Double.parseDouble(amount);
                         connection.createStatement().executeUpdate("update user_balance set balance='"+ (balance-withdrawamount)+"' where account_number='"+account_number+"';");
                        ResultSet rs2 = connection.createStatement().executeQuery("select*from user_balance where account_number = '"+account_number+"'");
                       if(rs2.next())
                      {
                       balance=     rs2.getDouble("balance");
                      }
                       
                       String trquery = "insert into transactionstatement (tr_date_time,tr_amount,tr_type,account_number, final_balance) values " 
                               +"(?,?,?,?,?)";
                       PreparedStatement pst = connection.prepareStatement(trquery);
                       Date today = new Date();
                       Timestamp tr_date_time = new Timestamp(today.getTime());
                       pst.setTimestamp(1, tr_date_time);
                       pst.setDouble(2, withdrawamount);
                       pst.setString(3,"Debit");
                       pst.setLong(4, account_number);
                       pst.setDouble(5, balance);

                        String trquery1 = "insert into transactionstatement_interest (tr_date_time,tr_amount,tr_type,account_number, final_balance) values "
                                +"(?,?,?,?,?)";
                        PreparedStatement pst1 = connection.prepareStatement(trquery1);
                        pst1.setTimestamp(1, tr_date_time);
                        pst1.setDouble(2, withdrawamount);
                        pst1.setString(3,"Debit");
                        pst1.setLong(4, account_number);
                        pst1.setDouble(5, balance);
                       pst1.executeUpdate();
                       int i = pst.executeUpdate();
                       System.out.println("transaction inserted "+i);
                        JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully\nRemaining balance:"+balance);

                    }


                    setVisible(false);
                    new transaction(account_number).setVisible(true);
                }
            }else if(e.getSource()==back){
                setVisible(false);
                new transaction(account_number).setVisible(true);
            }
        }catch(Exception e1){
            JOptionPane.showMessageDialog(null,e1.getMessage());
            System.out.println(e1);
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new withdraw(acc_no);
    }
}