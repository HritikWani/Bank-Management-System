package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin_login extends JFrame implements ActionListener {
    JButton login,back;
    JTextField pass_ad_field,admin_id_field;
    JLabel password_ad,admin_id;

    public admin_login(){
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


        admin_id=new JLabel("ADMIN ID:");
        admin_id.setBounds(500,250,100,50);
        add(admin_id);

        password_ad=new JLabel("PASSWORD:");
        password_ad.setBounds(500,350,100,50);
        add(password_ad);

        admin_id_field=new JTextField();
        admin_id_field.setBounds(700,250,300,50);
        add(admin_id_field);

        pass_ad_field=new JPasswordField();
        pass_ad_field.setBounds(700,350,300,50);
        add(pass_ad_field);

        back=new JButton("BACK");
        back.setBounds(500,450,100,50);
        add(back);
        back.addActionListener(this);

        login=new JButton("LOGIN");
        login.setBounds(800,450,100,50);
        add(login);
        login.addActionListener(this);

        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource()==back){
                setVisible(false);
                new select_login().setVisible(true);
                dispose();
            }
            else if(e.getSource()==login){
                String username= admin_id_field.getText();
                String password =   pass_ad_field.getText();
                if(username.equals("admin")&& password.equals("hapn")){
                setVisible(false);
               
           new AdminPage().setVisible(true);
                }
            }
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }

    public static void main(String[] args) {
        new admin_login();
    }
}
