package bank.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
import java.util.Random;

public class kyc extends JFrame implements ActionListener {
    long random;
    JLabel balance_label,adhar_number,Name,f_name,m_name,l_name,formno,PersonalDetails,moName,mo_f_name,dob,gender,emailid,address,city,state,pincode;
    JTextField balance_text,adhar_number_text,first_name,middle_name,last_name,monameTextField,emailidTextField,addressTextField,cityTextField,stateTextField,pincodeTextField;
    JButton next,back;
    JDateChooser dateChooser;
    JRadioButton male,female,other;

    public kyc() {
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


        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

       

        PersonalDetails = new JLabel("PERSONAL DETAILS " );
        PersonalDetails.setFont(new Font("Times New Roman", Font.BOLD, 22));
        PersonalDetails.setBounds(105,100,400,30);
        add(PersonalDetails);

        Name = new JLabel(" Name: " );
        Name.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Name.setBounds(100,140,100,30);
        add(Name);

        first_name=new JTextField();
        first_name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        first_name.setBounds(300,140,150,30);
        add(first_name);

        f_name=new JLabel("FIRST NAME");
        f_name.setBounds(310,160,150,30);
        add(f_name);

        middle_name=new JTextField();
        middle_name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        middle_name.setBounds(450,140,150,30);
        add(middle_name);

        m_name=new JLabel("MIDDLE NAME");
        m_name.setBounds(470,160,150,30);
        add(m_name);

        last_name=new JTextField();
        last_name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        last_name.setBounds(600,140,150,30);
        add(last_name);

        l_name=new JLabel("LAST NAME");
        l_name.setBounds(630,160,150,30);
        add(l_name);

        moName = new JLabel(" Mother's Name: " );
        moName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        moName.setBounds(100,190,200,30);
        add(moName);

        monameTextField = new JTextField();
        monameTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        monameTextField.setBounds(300,190,150,30);
        add(monameTextField);

        mo_f_name=new JLabel("MOTHER NAME");
        mo_f_name.setBounds(310,210,150,30);
        add(mo_f_name);

        dob = new JLabel(" Date of Birth " );
        dob.setFont(new Font("Times New Roman", Font.BOLD, 20));
        dob.setBounds(100,240,200,30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,240,200,30);
        dateChooser.setForeground(new Color(105,105,105));
        add(dateChooser);

        gender = new JLabel(" Gender: " );
        gender.setFont(new Font("Times New Roman", Font.BOLD, 20));
        gender.setBounds(100,290,200,30);
        add(gender);

        male = new JRadioButton("MALE");
        male.setBounds(300,290,100,30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("FEMALE");
        female.setBounds(450,290,100,30);
        female.setBackground(Color.WHITE);
        add(female);

        other=new JRadioButton("OTHER");
        other.setBounds(600,290,100,30);
        other.setBackground(Color.WHITE);
        add(other);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);
        gendergroup.add(other);

        emailid = new JLabel(" Email Address: " );
        emailid.setFont(new Font("Times New Roman", Font.BOLD, 20));
        emailid.setBounds(100,340,200,30);
        add(emailid);

        emailidTextField = new JTextField();
        emailidTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        emailidTextField.setBounds(300,340,400,30);
        add( emailidTextField);

        adhar_number=new JLabel("Adhar Number:");
        adhar_number.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adhar_number.setBounds(100,390,200,30);
        add(adhar_number);

        adhar_number_text=new JTextField();
        adhar_number_text.setFont(new Font("Times New Roman", Font.BOLD, 14));
        adhar_number_text.setBounds(300,390,400,30);
        add(adhar_number_text);



        address = new JLabel(" Address: " );
        address.setFont(new Font("Times New Roman", Font.BOLD, 20));
        address.setBounds(100,440,200,30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        addressTextField.setBounds(300,440,400,30);
        add( addressTextField);


        city = new JLabel(" City: " );
        city.setFont(new Font("Times New Roman", Font.BOLD, 20));
        city.setBounds(100,490,200,30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        cityTextField.setBounds(300,490,400,30);
        add( cityTextField);


        state = new JLabel(" State: " );
        state.setFont(new Font("Times New Roman", Font.BOLD, 20));
        state.setBounds(100,540,200,30);
        add(state);


        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        stateTextField.setBounds(300,540,400,30);
        add( stateTextField);

        pincode = new JLabel(" PIN CODE: " );
        pincode.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pincode.setBounds(100,590,200,30);
        add(pincode);

        pincodeTextField = new JTextField();
        pincodeTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pincodeTextField.setBounds(300,590,400,30);
        add( pincodeTextField);

        balance_label=new JLabel("Amount To Deposit: ");
        balance_label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        balance_label.setBounds(100,640,200,30);
        add(balance_label);

        balance_text=new JTextField();
        balance_text.setFont(new Font("Times New Roman", Font.BOLD, 14));
        balance_text.setBounds(300,640,400,30);
        add(balance_text);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Times New Roman" , Font.BOLD, 14));
        next.setBounds(620,700,80,30);
        next.addActionListener(this);
        add(next);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Times New Roman" , Font.BOLD, 14));
        back.setBounds(500,700,80,30);
        back.addActionListener(this);
        add(back);


        setLayout(null);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
         if (e.getSource()==back){
                setVisible(false);
         
                new select_login().setVisible(true);
                dispose();
            }else{
             
         
        
        String d_first_name = first_name.getText();
        String d_middle_name = middle_name.getText();
        String d_last_name = last_name.getText();
        String d_mo_f_name = monameTextField.getText();
        String d_dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String d_gender = "";
        if (male.isSelected()) {
            d_gender = "MALE";
        } else if (female.isSelected()) {
            d_gender = "FEMALE";
        }

        String d_emailid = emailidTextField.getText();
        String d_adhar_number=adhar_number_text.getText();
        String d_address = addressTextField.getText();
        String d_city = cityTextField.getText();
        String d_state = stateTextField.getText();
        String d_pincode = pincodeTextField.getText();
        String amount = balance_text.getText();
        double balance=0;
        Random ran = new Random();
        long account_number = (ran.nextLong(1,9999)) + 50409300000L;


        try {
            if (d_first_name.equals("") || d_middle_name.equals("") || d_last_name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name field is empty");
            } else if (!d_first_name.matches("^[a-zA-Z\\s]+$") || !d_middle_name.matches("^[a-zA-Z\\s]+$") || !d_last_name.matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(null, "Name should contain only letters and spaces");
            }  else if (d_mo_f_name.equals("")) {
                JOptionPane.showMessageDialog(null, "Father's Name is required");
            } else if (d_emailid.equals("")) {
                JOptionPane.showMessageDialog(null, "Email is required");
            } else if (!d_emailid.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                JOptionPane.showMessageDialog(null, "Invalid email format");
            } else if (d_adhar_number.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Adhar number is required");
            } else if (!d_adhar_number.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null,"Adhar number can be numeric only");
            } else if (d_address.equals("")) {
                JOptionPane.showMessageDialog(null, "Address is required");
            } else if (d_city.equals("")) {
                JOptionPane.showMessageDialog(null, "CITY NAME is required");
            } else if (d_state.equals("")) {
                JOptionPane.showMessageDialog(null, "STATE NAME is required");
            } else if (d_pincode.equals("")) {
                JOptionPane.showMessageDialog(null, "PINCODE is required");
//            }else if (!amount.matches("[0-9]+")) {
//                JOptionPane.showMessageDialog(null,"Balance can be numeric only");
            }else if (Double.parseDouble(amount)<500) {
                JOptionPane.showMessageDialog(null,"Min balance must be 500");
            } else if(e.getSource()==next) {
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
                String query = "insert into kyc values('"+account_number+"','" + d_first_name + "','" + d_middle_name+"','"+ d_last_name+ "','" + d_mo_f_name + "','" + d_dob + "','"+ d_gender +"','" + d_emailid + "', '" +d_adhar_number+ "', '" + d_address + "','" + d_city + "','" + d_state + "','" + d_pincode + "');";
                connection.createStatement().executeUpdate(query);
                connection.createStatement().executeUpdate("insert into user_balance values('"+account_number+"','"+amount+"');");
                ResultSet rs3=connection.createStatement().executeQuery("select*from user_balance where account_number='"+account_number+"';");
                if(rs3.next()) {
                    balance=rs3.getDouble("balance");
                }
                String trquery = "insert into transactionstatement (tr_date_time,tr_amount,tr_type,account_number,final_balance) values "+"(?,?,?,?,?);";
                PreparedStatement pst = connection.prepareStatement(trquery);
                Date today = new Date();
                Timestamp tr_date_time = new Timestamp(today.getTime());
                pst.setTimestamp(1, tr_date_time);
                pst.setDouble(2, Double.parseDouble(amount));
                pst.setString(3,"Credit");
                pst.setLong(4, account_number);
                pst.setDouble(5, balance);
                pst.executeUpdate();

                String trquery1 = "insert into transactionstatement_interest (tr_date_time,tr_amount,tr_type,account_number,final_balance) values "+"(?,?,?,?,?);";
                PreparedStatement pst1 = connection.prepareStatement(trquery1);
                pst1.setTimestamp(1, tr_date_time);
                pst1.setDouble(2, Double.parseDouble(amount));
                pst1.setString(3,"Credit");
                pst1.setLong(4, account_number);
                pst1.setDouble(5, balance);
                pst1.executeUpdate();

                connection.close();

                setVisible(false);
                new select_username(account_number).setVisible(true);
            }
            
        
        } catch (Exception e1) {
            System.out.println(e1);
        }}
    }
    
    public static void main(String[] args) {
        new kyc();
    }
}
