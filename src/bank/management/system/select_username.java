package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;


public class select_username extends JFrame implements ActionListener {
    JLabel username,label_select_user_name,password;
    JTextField select_user_name,text_password;
    JButton submit;
    static long acc_no;
    long account_number;
    public select_username(long account_number){
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

        

 

        label_select_user_name=new JLabel("PLEASE CREATE A USERNAME AND PASSWORD");
        label_select_user_name.setBounds(200,200,800,50);
        label_select_user_name.setFont(new Font("Times New Roman",Font.BOLD,32));
        add(label_select_user_name);

        username=new JLabel("Username:");
        username.setBounds(200,300,100,30);
        username.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(username);

        select_user_name=new JTextField();
        select_user_name.setBounds(300,300,200,30);
        select_user_name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(select_user_name);

        password=new JLabel("Password:");
        password.setBounds(200,350,100,30);
        password.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(password);

        text_password=new JTextField();
        text_password.setBounds(300,350,200,30);
        text_password.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(text_password);

        submit=new JButton("Submit");
        submit.setBounds(500,450,100,30);
        submit.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(submit);
        submit.addActionListener(this);

        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        String d_select_user_name = select_user_name.getText();
        String d_text_password = text_password.getText();

        try{
            if(d_select_user_name.equals("")) {
                JOptionPane.showMessageDialog(null, "Username field empty");
            }
            else if(d_text_password.equals("")) {
                JOptionPane.showMessageDialog(null, "Password field empty");
            }
            else if(e.getSource()==submit){
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
                String query = "insert into user_login values('"+account_number+"','"+d_select_user_name+"','"+d_text_password+"');";
                connection.createStatement().executeUpdate(query);

                connection.close();
                setVisible(false);
                new user_login().setVisible(true);
            }
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }

    public static void main(String[] args) {
        new select_username(acc_no);
    }
}
