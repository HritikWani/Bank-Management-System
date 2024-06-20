package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class service extends JFrame implements ActionListener {
    JLabel l1;
    JButton saving_acc;
    public service(){
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


        l1=new JLabel("PLEASE SELECT YOUR PREFERRED SERVICE...");
        l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(450,250,600,50);
        add(l1);

        saving_acc=new JButton("SAVING ACCOUNT");
        saving_acc.setFont(new Font("Times New Roman", Font.BOLD, 15));
        saving_acc.setBounds(625,350,200,100);
        add(saving_acc);
        saving_acc.addActionListener(this);

        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource()==saving_acc){
                setVisible(false);
                new kyc().setVisible(true);
            }
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }

    public static void main(String[] args) {
        new service();
    }
}

