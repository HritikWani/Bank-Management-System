/*
                                                                                                                                              * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class UpdateKYC extends JFrame  implements ActionListener {
    
    private long account_number;
    JLabel adhar_number,Name,f_name,m_name,l_name,formno,PersonalDetails,moName,mo_f_name,dob,gender,emailid,address,city,state,pincode,username;
    JTextField adhar_number_text,first_name,middle_name,last_name,monameTextField,emailidTextField,addressTextField,cityTextField,stateTextField,pincodeTextField,select_username;
    JButton update;
  JButton edit;
    JDateChooser dateChooser;
    JRadioButton male,female,other,married,unmarried;


    public UpdateKYC()  {
    }

    public UpdateKYC(long account_number)  {
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


        
        
        
//         ImageIcon imageIcon = new ImageIcon("./logo2.jpg"); // Specify the path to your image file
//
//        // Create a label to hold the image
//        JLabel label = new JLabel();
//        label.setIcon(imageIcon);
//        label.setBounds(475,25,150,95);
//        add(label);
//        //bank label
//        JLabel label1=new JLabel("HAPN BANK");
//        label1.setFont(new Font("Times New Roman", Font.BOLD, 40));
//       label1.setBackground(Color.LIGHT_GRAY);
//     //  label1.setForeground(Color.DARK_GRAY);
// //      label1.setOpaque(true);
//        label1.setBounds(640,50,250,70);
//        add(label1);


           //Data Variables
           String d_first_name ="";
        String d_middle_name="" ;
        String d_last_name="" ;
        String d_mo_f_name="";
       // String d_dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
       String d_dob="";
        String d_gender = "";
 //           d_gender = "MALE";
   //         d_gender = "FEMALE";
        

        String d_emailid="" ;
        String d_adhar_number="";
        String d_address="";
        String d_city="" ;
        String d_state="";
        String d_pincode ="" ;
           try{
         
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from kyc where account_number  = " + account_number);
                    if (rs1.next()){
                            d_first_name= rs1.getString("first_name");
                            d_middle_name = rs1.getString("middle_name");
                            d_last_name = rs1.getString("last_name");
                            d_mo_f_name = rs1.getString("mother_name");
                            d_dob = rs1.getString("dob");
                            d_gender= rs1.getString("gender");
                            d_emailid = rs1.getString("email_id");
                            d_adhar_number = rs1.getString("adhar_number");
                            d_address = rs1.getString("address");
                            d_city = rs1.getString("city");
                            d_state = rs1.getString("state");
                            d_pincode = rs1.getString("pincode");
                            
                            
                            
                            
                            
                    }else {
                                     JOptionPane.showMessageDialog(null, ("Account Number Does not exist"));

                    }
                    
              

                
            } catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex.getMessage());

                ex.printStackTrace();
            }
        
           
           
            formno = new JLabel("ACCOUNT NUMBER  " + account_number );
        formno.setFont(new Font("Times New Roman", Font.BOLD, 38));
        formno.setBounds(100,20,650,40);
        add(formno);

        PersonalDetails = new JLabel("Personal Details " );
        PersonalDetails.setFont(new Font("Times New Roman", Font.BOLD, 22));
        PersonalDetails.setBounds(100,80,400,30);
        add(PersonalDetails);

        Name = new JLabel(" Name: " );
        Name.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Name.setBounds(100,140,100,30);
        add(Name);

        first_name=new JTextField();
        first_name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        first_name.setBounds(300,140,150,30);
        first_name.setText(d_first_name);
        first_name.setEditable(false);
        add(first_name);
        
        

        f_name=new JLabel("FIRST NAME");
        f_name.setBounds(310,160,150,30);
        add(f_name);

        middle_name=new JTextField();
        middle_name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        middle_name.setBounds(450,140,150,30);
        middle_name.setText(d_middle_name);
        middle_name.setEditable(false);
        add(middle_name);

        m_name=new JLabel("MIDDLE NAME");
        m_name.setBounds(470,160,150,30);
        add(m_name);

        last_name=new JTextField();
        last_name.setFont(new Font("Times New Roman", Font.BOLD, 14));
        last_name.setBounds(600,140,150,30);
        last_name.setText(d_last_name);
        last_name.setEditable(false);
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
        monameTextField.setText(d_mo_f_name);
        monameTextField.setEditable(false);
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
        Date dob=null;
        try {
            dob = new SimpleDateFormat("dd-MMM-yyyy").parse(d_dob);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateKYC.class.getName()).log(Level.SEVERE, null, ex);
        }
      dateChooser.setDate(dob);
      dateChooser.setEnabled(false);
        add(dateChooser);

        gender = new JLabel(" Gender: " );
        gender.setFont(new Font("Times New Roman", Font.BOLD, 20));
        gender.setBounds(100,290,200,30);
        add(gender);

        male = new JRadioButton("MALE");
        male.setBounds(300,290,100,30);
        male.setBackground(Color.WHITE);
        male.setEnabled(false);
        add(male);

        female = new JRadioButton("FEMALE");
        female.setBounds(450,290,100,30);
        female.setBackground(Color.WHITE);
        female.setEnabled(false);
        add(female);

        other=new JRadioButton("OTHER");
        other.setBounds(600,290,100,30);
        other.setBackground(Color.WHITE);
        other.setEnabled(false);
        add(other);
        
        if(d_gender.equals("MALE")){
            male.setSelected(true);
            
        }else if(d_gender.equals("FEMALE")){
            female.setSelected(true);
        }

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
        emailidTextField.setText(d_emailid);
        emailidTextField.setEditable(false);
        add( emailidTextField);

        adhar_number=new JLabel("Adhar Number:");
        adhar_number.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adhar_number.setBounds(100,390,200,30);
        add(adhar_number);

        adhar_number_text=new JTextField();
        adhar_number_text.setFont(new Font("Times New Roman", Font.BOLD, 14));
        adhar_number_text.setBounds(300,390,400,30);
        adhar_number_text.setText(d_adhar_number);
        adhar_number_text.setEditable(false);
        add(adhar_number_text);



        address = new JLabel(" Address: " );
        address.setFont(new Font("Times New Roman", Font.BOLD, 20));
        address.setBounds(100,440,200,30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        addressTextField.setBounds(300,440,400,30);
        addressTextField.setText(d_address);
        addressTextField.setEditable(false);
        add( addressTextField);


        city = new JLabel(" City: " );
        city.setFont(new Font("Times New Roman", Font.BOLD, 20));
        city.setBounds(100,490,200,30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        cityTextField.setBounds(300,490,400,30);
        cityTextField.setText(d_city);
        cityTextField.setEditable(false);
        add( cityTextField);


        state = new JLabel(" State: " );
        state.setFont(new Font("Times New Roman", Font.BOLD, 20));
        state.setBounds(100,540,200,30);
        add(state);


        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        stateTextField.setBounds(300,540,400,30);
        stateTextField.setText(d_state);
        stateTextField.setEditable(false);
        add( stateTextField);

        pincode = new JLabel(" PIN CODE: " );
        pincode.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pincode.setBounds(100,590,200,30);
        add(pincode);

        pincodeTextField = new JTextField();
        pincodeTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pincodeTextField.setBounds(300,590,400,30);
        pincodeTextField.setText(d_pincode);
        pincodeTextField.setEditable(false);
        add( pincodeTextField);
        
//        back = new JButton("BACK");
//        back.setBounds(490,470,150,35);
//        add(back);
//        back.addActionListener(this);


        update = new JButton("UPDATE");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Times New Roman" , Font.BOLD, 14));
        update.setBounds(500,660,180,30);
        update.addActionListener(this);
                        update.setEnabled(false);

        add(update);
        
        edit = new JButton("EDIT");
        edit.setBackground(Color.BLACK);
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Times New Roman" , Font.BOLD, 14));
        edit.setBounds(200,660,180,30);
        edit.addActionListener(this);
        add(edit);

        


           
                setLayout(null);
                setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==edit){
                    first_name.setEditable(true);
                   middle_name.setEditable(true);
                   last_name.setEditable(true);
                   monameTextField.setEditable(true);
                  dateChooser.setEnabled(true);
                   male.setEnabled(true);
                  female.setEnabled(true);
                   other.setEnabled(true);
                     emailidTextField.setEditable(true);
               adhar_number_text.setEditable(true);
                addressTextField.setEditable(true);
              cityTextField.setEditable(true);
              stateTextField.setEditable(true);
            pincodeTextField.setEditable(true);

                update.setEnabled(true);
           
        }else if(e.getSource()==update){
            update.setEnabled(false);
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
            } else if(e.getSource()==update) {
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
                 String query ="update kyc set "+
                         "first_name = ? ,"+
                         "middle_name = ? ,"+
                         "last_name = ? ,"+
                         "mother_name = ? ,"+
                         "dob = ? ,"+
                         "gender = ? ,"+
                         "email_id = ? ,"+
                         "adhar_number = ? ,"+
                         "address = ? ,"+
                         "city = ? ,"+
                         "state = ? ,"+
                         "pincode = ?"+
                         " where account_number = ?";
                 PreparedStatement pst = connection.prepareStatement(query);
                 pst.setString(1, d_first_name);
                 pst.setString(2, d_middle_name);
                 pst.setString(3, d_last_name);
                 pst.setString(4,d_mo_f_name);
                 pst.setString(5, d_dob);
                 pst.setString(6,d_gender);
                 pst.setString(7, d_emailid);
                 long adharno = Long.parseLong(d_adhar_number);
                 pst.setLong(8, adharno);
                 pst.setString(9, d_address);
                 pst.setString(10, d_city);
                 pst.setString(11, d_state);
                 int pincode = Integer.parseInt(d_pincode);
                 pst.setInt(12, pincode);
                 pst.setLong(13, account_number);
                 int u = pst.executeUpdate();
             System.out.println("KYC Updated successfully");
             JOptionPane.showMessageDialog(null," KYC Updated successfully");
              connection.close();

                setVisible(false);
                new AdminPage().setVisible(true);
                dispose();
            }
//            else if (e.getSource()==back){
//                setVisible(false);
//         
//                new AdminPage().setVisible(true);
//                dispose();
//            }
            
        } catch (Exception e1) {
            System.out.println(e1);
                         JOptionPane.showMessageDialog(null,e1.getMessage());

        }
        }
    }
    
}
