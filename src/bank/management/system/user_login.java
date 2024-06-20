package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class user_login extends JFrame implements ActionListener {
    JButton login,back;
    JTextField pass_us_field,user_id_field;
    JLabel password_us,user_id;

    public user_login(){
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

        user_id=new JLabel("USER ID:");
        user_id.setBounds(500,250,100,50);
        add(user_id);

        password_us=new JLabel("PASSWORD:");
        password_us.setBounds(500,350,100,50);
        add(password_us);

        user_id_field=new JTextField();
        user_id_field.setBounds(700,250,300,50);
        add(user_id_field);

        pass_us_field=new JPasswordField();
        pass_us_field.setBounds(700,350,300,50);
        add(pass_us_field);

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
        String userid=user_id_field.getText();
        String password=pass_us_field.getText();
        long account_number=0;

        try{
            if(e.getSource()==back){
                setVisible(false);
                new select_login().setVisible(true);
            }
            else if(e.getSource()==login){
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
                String query1 = "select*from user_login where username='"+userid+"';";
                ResultSet rs= connection.createStatement().executeQuery(query1);
                if(rs.next()) {
                    account_number = rs.getLong("account_number");
                }else{
                  JOptionPane.showMessageDialog(null,"Username and password does not exists");

                }
                if(rs.getString("username").equals(userid) && rs.getString("password").equals(password)){
                    setVisible(false);
                    new transaction(account_number).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Username and password does not match");
                }
                connection.close();
            }
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }

    public static void main(String[] args) {
        new user_login();
    }
}
