/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class MiniStatement extends JFrame implements ActionListener{
private long account_number;
private JButton back,show,clear,print;
private JLabel from_lbl,to_lbl;
private JScrollPane sp;
private JTable jt;
private JDateChooser from_dateChooser , to_dateChooser;
private String column[]={"Transaction ID","Date & Time","Amount","Type","Final Balance"};      
//JFrame f;    
    public MiniStatement() {
    }
     public MiniStatement(long account_number) {
        this.account_number = account_number;
//           f=new JFrame();    
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
           
            JLabel     acno = new JLabel();
            acno.setFont(new Font("Times New Roman", Font.BOLD, 20));
            acno.setBounds(30, 150, 350, 35);
            acno.setText("Account Number = "+account_number);
            add(acno);
            
            from_dateChooser = new JDateChooser();
            from_dateChooser.setBounds(400,150,200,35);
            from_dateChooser.setForeground(new Color(105,105,105));
            from_dateChooser.setDateFormatString("yyyy-MM-dd");
            add(from_dateChooser);
            
            from_lbl=new JLabel();
            from_lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
            from_lbl.setBounds(440, 180, 220, 35);
            from_lbl.setText("From Date ");
            add(from_lbl);
            
             to_dateChooser = new JDateChooser();
            to_dateChooser.setBounds(700,150,200,35);
            to_dateChooser.setForeground(new Color(105,105,105));
              to_dateChooser.setDateFormatString("yyyy-MM-dd");
            add(to_dateChooser);
            
            to_lbl=new JLabel();
            to_lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
            to_lbl.setBounds(740, 180, 220, 35);
            to_lbl.setText("To Date ");
            add(to_lbl);
            
             show = new JButton("Show");
           show.setBounds(1000, 150, 100, 35);
           add(show);
           show.addActionListener(this);

            clear = new JButton("Clear");
           clear.setBounds(1150, 150, 100, 35);
           add(clear);
           clear.addActionListener(this);
           
           print = new JButton("Print");
           print.setBounds(1300, 150, 100, 35);
           add(print);
           print.addActionListener(this);
           
            
             String query="select * from transactionstatement where account_number="+account_number;
            String data[][] =null;
            String countquery = "select  count(*) as rowc from transactionstatement where account_number="+account_number;
            try {
                data=        this.loadtransaction(query,countquery);
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null,ex.getMessage());
            }
               
            
            jt=new JTable(data,column);    
             // jt.setBounds(30,400,1000,300);          
            sp=new JScrollPane(jt);    
            sp.setBounds(30,300,1500,400);
            add(sp);          
            
             back = new JButton("BACK");
           back.setBounds(200, 710, 200, 35);
           add(back);
           back.addActionListener(this);
           setTitle("BANK MANAGEMENT SYSTEM");
            setSize(1540,800);    
            setVisible(true);

          
     }
      public void actionPerformed(ActionEvent ae) {
          String query="select * from transactionstatement where account_number="+account_number;
          String countquery = "select  count(*) as rowc from transactionstatement where account_number="+account_number;

        try{
            
            if (ae.getSource()==back){
                setVisible(false);
         
                new transaction(account_number).setVisible(true);
                dispose();
            }else if(ae.getSource()==clear){
                ((JTextField) from_dateChooser.getDateEditor().getUiComponent()).setText("");
                ((JTextField) to_dateChooser.getDateEditor().getUiComponent()).setText("");
                  String data[][]=loadtransaction(query,countquery);
              jt.setModel(new DefaultTableModel(data,column));
            } else if(ae.getSource()==show) {
                
              
               String from_date = ((JTextField) from_dateChooser.getDateEditor().getUiComponent()).getText();
               String to_date = ((JTextField) to_dateChooser.getDateEditor().getUiComponent()).getText();
               if( !from_date.isBlank() && !to_date.isBlank()) {
                   query="select * from transactionstatement where account_number="+account_number + 
                           " and tr_date_time between '"+from_date+" 00:00:00' and '"+to_date+" 23:59:59'";
                   countquery = "select  count(*) as rowc from transactionstatement "+
                           " where account_number="+account_number + 
                           " and tr_date_time between '"+from_date+" 00:00:00' and '"+to_date+" 23:59:59'";
               }
                 String data[][]=loadtransaction(query,countquery);
                 jt.setModel(new DefaultTableModel(data,column));
             } else if(ae.getSource()==print) {
                 print();
             }
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
      
      private String[][] loadtransaction(String trquery,String countquery) throws Exception{
         int rowcount =0;
         String data[][] = null;
         try{
              Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");

           ResultSet rs1 = connection.createStatement().executeQuery(countquery);
           if (rs1.next()){
               rowcount = rs1.getInt(1);
           }
          

           ResultSet rs2= connection.createStatement().executeQuery(trquery);
           
             data = new String[rowcount][5];
           int row = 0;
           while(rs2.next()){
            data[row][0] = rs2.getString("transaction_id");
            data[row][1] = rs2.getString("tr_date_time");
            data[row][2] = rs2.getString("tr_amount");
            data[row][3] = rs2.getString("tr_type");
            data[row][4] = rs2.getString("final_balance");

            row++;
           }
        
     }
         catch(Exception e){
             e.printStackTrace();
             throw e;
         }
            return data;
     }
     
    private void print() {
        Document document = new Document(PageSize.A4.rotate());
        try {
            String date=LocalDate.now().toString();
            LocalTime t=LocalTime.now();
            String time=t.getHour()+"-"+t.getMinute()+"-"+t.getSecond();
            String filename="./report/Statement _"+account_number+"_"+date+"_"+time+".pdf"; 

            PdfWriter writer =
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            
            document.open();
            
            com.lowagie.text.Image image =com.lowagie.text.Image.getInstance("logo2.jpg"); //For adding image in the pdf.
             image.scaleAbsolute(150, 95);
            //image.setAbsolutePosition(490f, 750f);
            document.add(image);
            
             document.add(new Paragraph(("HAPN Bank") , FontFactory.getFont(FontFactory.TIMES_BOLD, 40,Font.BOLD, Color.black))); 
              
              document.add(new Paragraph("Account Number :- "+account_number , FontFactory.getFont(FontFactory.TIMES_BOLD, 16,Font.BOLD, Color.black))); 
               
              String from_date = ((JTextField) from_dateChooser.getDateEditor().getUiComponent()).getText();
               String to_date = ((JTextField) to_dateChooser.getDateEditor().getUiComponent()).getText();
              String st="Statement From Date :- "+from_date +"       To Date :- "+to_date;
               document.add(new Paragraph(st , FontFactory.getFont(FontFactory.TIMES_BOLD, 16,Font.BOLD, Color.black))); 
             
              
             document.add(new Paragraph("Created at :- "+LocalDateTime.now().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 16,Font.BOLD, Color.black))); // Current date and time 
            document.add( Chunk.NEWLINE );
             document.add( Chunk.NEWLINE );
             
            PdfPTable tbl = new PdfPTable(5);
            tbl.setTotalWidth(600f);
             tbl.setHorizontalAlignment(1);
            
  
             tbl.addCell("Transaction ID");
            tbl.addCell("Date & Time");
             tbl.addCell("Amount");
             tbl.addCell("Type");
            tbl.addCell("Final Balance");

        
            for (int i = 0; i < jt.getRowCount(); i++) {
                String transactionID = jt.getValueAt(i, 0).toString();
                String datetime = jt.getValueAt(i, 1).toString();
                String amount = jt.getValueAt(i, 2).toString();
                String type = jt.getValueAt(i, 3).toString();
                String fbal = jt.getValueAt(i, 4).toString();
           

                tbl.addCell(transactionID);
                tbl.addCell(datetime);
                tbl.addCell(amount);
                tbl.addCell(type);
                tbl.addCell(fbal);
    

             }
            document.add(tbl);
            JOptionPane.showMessageDialog(null,"Created pdf successfully");
        } catch (Exception e) {
        	e.printStackTrace();
            System.err.println(e.getMessage());
        }
        
        document.close();
     }
//     public String[][] loadtransaction(long account_number){
//         int rowcount =0;
//         String data[][] = null;
//         try{
//              Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
//           String countquery = "select  count(*) as rowc from transactionstatement where account_number ="+ account_number;
//           ResultSet rs1 = connection.createStatement().executeQuery(countquery);
//      //     ResultSet rs3 = connection.createStatement().executeQuery("select * from kyc where account_number='"+account_number+"';");
//           if (rs1.next()){
//               rowcount = rs1.getInt(1);
//           }
//        
//            data = new String[rowcount][5];
//           String trquery = "select * from transactionstatement where account_number ="+ account_number;
//           ResultSet rs2= connection.createStatement().executeQuery(trquery);
//           int row = 0;
//           while(rs2.next()){
//            data[row][0] = rs2.getString("transaction_id");
//            data[row][1] = rs2.getString("tr_date_time");
//            data[row][2] = rs2.getString("tr_amount");
//            data[row][3] = rs2.getString("tr_type");
//            data[row][4] = rs2.getString("final_balance");
//            row++;
//           }
//        
//     }
//         catch(Exception e){
//             e.printStackTrace();
//         }
//            return data;
//     }
}
