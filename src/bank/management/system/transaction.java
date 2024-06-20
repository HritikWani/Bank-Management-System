package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class transaction extends JFrame implements ActionListener {
    JLabel tran;
    JButton deposit,withdraw,mini_statement,balance_enquiry,transfer_money,exit;
    static long acc_no;
    long account_number;
    public transaction(long account_number) {
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

        
            JLabel     acno = new JLabel();
            acno.setFont(new Font("Times New Roman", Font.BOLD, 40));
            acno.setBounds(300, 150, 700, 35);
            acno.setText("Account Number : "+account_number);
            add(acno);

        tran = new JLabel("Please Select Your Transaction");
        tran.setFont(new Font("Times New Roman", Font.BOLD, 40));
        tran.setBounds(300, 200, 700, 100);
        add(tran);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(300, 299, 200, 50);
        deposit.addActionListener(this);
        add(deposit);

        withdraw = new JButton("CASH WITHDRAWAL");
        withdraw.setBounds(520, 299, 200, 50);
        withdraw.addActionListener(this);
        add(withdraw);

        mini_statement = new JButton("MINI STATEMENT");
        mini_statement.setBounds(300, 370, 200, 50);
        mini_statement.addActionListener(this);
        add(mini_statement);

        balance_enquiry = new JButton("BALANCE ENQUIRY");
        balance_enquiry.setBounds(520, 370, 200, 50);
        balance_enquiry.addActionListener(this);
        add(balance_enquiry);
        
         transfer_money = new JButton("TRANSFER MONEY");
        transfer_money.setBounds(300, 440, 200, 50);
        transfer_money.addActionListener(this);
        add(transfer_money);


        exit = new JButton("EXIT");
        exit.setBounds(520, 440, 200, 50);
        exit.addActionListener(this);
        add(exit);

        setLayout(null);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource()==withdraw){
                setVisible(false);
                new withdraw(account_number).setVisible(true);
              dispose();
            } else if (e.getSource()==deposit) {
                setVisible(false);
                new deposit(account_number).setVisible(true);
                              dispose();
            } else if (e.getSource()==mini_statement) {
                setVisible(false);
                new MiniStatement(account_number);
                              dispose();
            } else if (e.getSource()==balance_enquiry) {
                setVisible(false);
                new balance_enquiry(account_number).setVisible(true);
                              dispose();
            } else if (e.getSource()==exit) {
                setVisible(false);
                new select_login().setVisible(true);
                              dispose();
            }else if(e.getSource()==transfer_money){
                setVisible(false);
                new TransferMoney(account_number);
                              dispose();
            }
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }

    public static void main(String[] args) {
        new transaction(acc_no);
    }
}

