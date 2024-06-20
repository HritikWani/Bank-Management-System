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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class AdminPage extends JFrame implements ActionListener{
JButton update;
JButton delete;
JButton viewtransactions;
JButton exit;

    public AdminPage()  {
          setTitle("BANK MANAGEMENT SYSTEM");
        setSize(1540,820);
        setLayout(null);
        
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


        JLabel tran = new JLabel("Admin Menu");
        tran.setFont(new Font("Times New Roman", Font.BOLD, 40));
        tran.setBounds(300,225, 700, 35);
        add(tran);

        update= new JButton("UPDATE KYC");
        update.setBounds(300, 299, 200, 50);
        update.addActionListener(this);
        add(update);
        
        delete= new JButton("DELETE ACCOUNT");
        delete.setBounds(520, 299, 200, 50);
        delete.addActionListener(this);
        add(delete);

         viewtransactions = new JButton("VIEW ALL TRANSACTIONS");
         viewtransactions.setBounds(300, 370, 200, 50);
         viewtransactions.addActionListener(this);
        add( viewtransactions);

        exit = new JButton("EXIT");
       exit.setBounds(520, 370, 200, 50);
    exit.addActionListener(this);
        add(exit);
        
        setVisible(true);
                



    }
    
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== viewtransactions){
            this.setVisible(false);
            new ViewAllTransactions().setVisible(true);
            dispose();
        }
        else if (e.getSource()==exit) {
                setVisible(false);
                new select_login().setVisible(true);
                dispose();
        }else if (e.getSource()==delete){
                setVisible(false);
                new DeleteAccount().setVisible(true);
           dispose();
        }  else if (e.getSource()==update){
                setVisible(false);
                new FetchKYC().setVisible(true);
                dispose();
        }
        
    }
    
}
