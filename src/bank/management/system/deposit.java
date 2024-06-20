package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class deposit extends JFrame implements ActionListener {
    JTextField deposit_text;
    JButton depo,back;
    JLabel deposit_label;
    static long acc_no;
    long account_number;
    double balance,balance1;

    public deposit(long account_number){
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

       

            JLabel acno = new JLabel();
            acno.setFont(new Font("Times New Roman", Font.BOLD, 40));
            acno.setBounds(300, 150, 700, 35);
            acno.setText("Account Number : "+account_number);
            add(acno);

        deposit_label = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        deposit_label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        deposit_label.setBounds(300,250,400,30);
        add(deposit_label);

        deposit_text = new JTextField();
        deposit_text.setFont(new Font("Times New Roman", Font.BOLD, 22));
        deposit_text.setBounds(290,320,320,25);
        add(deposit_text);

        depo= new JButton("DEPOSIT");
        depo.setBounds(290,388,150,35);
        add(depo);
        depo.addActionListener(this);

        back = new JButton("BACK");
        back.setBounds(470,388,150,35);
        add(back);
        back.addActionListener(this);

        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String d_amount = deposit_text.getText();

        try{
         

            if(e.getSource()==back){
                setVisible(false);
                new transaction(account_number).setVisible(true);
                dispose();
            } else if(e.getSource()==depo){

                if(d_amount.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to deposit");
                }else{
                    double amount=Double.parseDouble(d_amount);
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
                    ResultSet rs2=connection.createStatement().executeQuery("select*from user_balance where account_number='"+account_number+"';");
                    if(rs2.next()) {
                        balance1=rs2.getDouble("balance");
                    }
                    double total_balance=balance1+amount;
                    connection.createStatement().executeUpdate("update user_balance set balance='"+total_balance+"' where account_number='"+account_number+"';");
                    ResultSet rs3=connection.createStatement().executeQuery("select*from user_balance where account_number='"+account_number+"';");
                    if(rs3.next()) {
                        balance=rs3.getDouble("balance");
                    }
                      String trquery = "insert into transactionstatement (tr_date_time,tr_amount,tr_type,account_number, final_balance) values " 
                               +"(?,?,?,?,?)";
                       PreparedStatement pst = connection.prepareStatement(trquery);
                       
                       Date today = new Date();
                       Timestamp tr_date_time = new Timestamp(today.getTime());
                       pst.setTimestamp(1, tr_date_time);
                       pst.setDouble(2, amount);
                       pst.setString(3,"Credit");
                       pst.setLong(4, account_number);
                       pst.setDouble(5, balance);

                    String trquery1 = "insert into transactionstatement_interest (tr_date_time,tr_amount,tr_type,account_number, final_balance) values "
                            +"(?,?,?,?,?)";
                    PreparedStatement pst1 = connection.prepareStatement(trquery1);
                    pst1.setTimestamp(1, tr_date_time);
                    pst1.setDouble(2, amount);
                    pst1.setString(3,"Credit");
                    pst1.setLong(4, account_number);
                    pst1.setDouble(5, balance);
                       pst1.executeUpdate();

                       int i = pst.executeUpdate();
                       System.out.println("transaction inserted "+i);
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Credited Successfully\nRemaining balance:"+balance);

                    setVisible(false);
                    
                    new transaction(account_number).setVisible(true);
                    dispose();
                    }
              
            }
        }catch(Exception e1){
            JOptionPane.showMessageDialog(null,e1.getMessage());
            System.out.println(e1);
        }

    }

    public static void main(String[] args){
        new deposit(acc_no);
    }
}
