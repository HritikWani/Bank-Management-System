package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class select_login extends JFrame implements ActionListener {
    JButton admin,user,new_account;
    
//    protected void paintComponent(Graphics g) {
//           //     super.paintComponent(g);
//                ImageIcon imageIcon = new ImageIcon("C:/Users/Admin/Documents/NetBeansProjects/BankMS/img4.jpg");
//                Image image = imageIcon.getImage();
//                g.drawImage(image, 100,0,1540,820, this);
//            }
//    
    public select_login(){
//        interest ob = new interest();
//        ob.calculateInetrest();
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

        admin=new JButton("ADMIN LOGIN");
        admin.setBounds(500,300,200,50);
        add(admin);
        admin.addActionListener(this);

        user=new JButton("USER LOGIN");
        user.setBounds(800,300,200,50);
        add(user);
        user.addActionListener(this);

        new_account=new JButton("OPEN A NEW ACCOUNT");
        new_account.setBounds(500,400,500,50);
        add(new_account);
        new_account.addActionListener(this);

        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource()==admin){
                setVisible(false);
                new admin_login().setVisible(true);
            }
            else if(e.getSource()==user){
                setVisible(false);
                new user_login().setVisible(true);
            }
            else if(e.getSource()==new_account){
                setVisible(false);
                new service().setVisible(true);
            }
        }
        catch(Exception e1){
            System.out.println(e1);
        }

    }

    public static void main(String[] args) {
        new select_login();
    }
}













