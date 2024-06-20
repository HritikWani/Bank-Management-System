package bank.management.system;

import javax.management.Query;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class interest {
    static Timestamp d1;
    static LocalDateTime d, d2;
    static Double balance, bal;


//    public static void main(String[] args) {
    public void calculateInetrest(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");

            ResultSet rs1 = connection.createStatement().executeQuery("select account_number from kyc;");
            while (rs1.next()) {
                long acc_no = rs1.getLong("account_number");
                ResultSet rs20 = connection.createStatement().executeQuery("select *from transactionstatement_interest where account_number='" + acc_no + "' order by transaction_id;");
                if (rs20.next()) {
                    d1 = rs20.getTimestamp("tr_date_time");
                }
                LocalDateTime dt = d1.toLocalDateTime();
                LocalDateTime d_now = LocalDateTime.now();
                Duration dura = Duration.between(dt, d_now);
                long duraDays = dura.toDays();

                while (duraDays > 30) {
                    ResultSet rs2 = connection.createStatement().executeQuery("select *from transactionstatement_interest where account_number='" + acc_no + "' order by transaction_id;");
                    d = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
                    long day = 0, h = 0, m = 0, s = 0, day2 = 0;
                    Queue<Double> blnc = new LinkedList<Double>();
                    int n = 0;
                    while (rs2.next() && !(n == 30)) {
                        bal=rs2.getDouble("final_balance");
                        d1 = rs2.getTimestamp("tr_date_time");
                        LocalDateTime for_d1 = d1.toLocalDateTime();
                        int t_year = for_d1.getYear();
                        int t_month = for_d1.getMonthValue();
                        int t_day = for_d1.getDayOfMonth();
                        Duration duration = Duration.between(d, for_d1);
                        long hours = duration.toHours();
                        long minutes = duration.toMinutes() % 60;
                        long seconds = duration.toSeconds() % 60;

                        if (hours < 0) {
                            hours = 23 + hours % 24;
                        }
                        if (minutes < 0) {
                            minutes = 59 + minutes;
                        }
                        if (seconds < 0) {
                            seconds = 60 + seconds;
                        }
                        h = h + hours;
                        m = m + minutes;
                        s = s + seconds;

                        while (s >= 60) {
                            m = m + 1;
                            s = s - 60;
                        }
                        while (m >= 60) {
                            h = h + 1;
                            m = m - 60;
                        }
                        while (h >= 24) {
                            day = day + 1;
                            h = h - 24;
                        }

                        System.out.println(d1);
                        d = for_d1;
                        if (!blnc.isEmpty()) {
                            if (day == day2) {
                                blnc.poll();
                            }
                        }

                        while (!(day == day2 + 1) && (n < 30)) {
                            blnc.offer(bal);
                            if (t_day >= 30) {
                                t_day = 0;
                                if (t_month == 12) {
                                    t_month = 0;
                                    t_year++;
                                } else {
                                    t_month++;
                                }
                            } else {
                                t_day++;
                            }
                            d = d.plusDays(1);
                            day2++;
                            n++;
                        }
                        if (n < 30) {
                            bal = rs2.getDouble("final_balance");
                            blnc.offer(bal);
                            connection.createStatement().executeUpdate("delete from transactionstatement_interest where account_number='" + acc_no + "' and tr_date_time='" + d1 + "';");
                            day2 = day;
                            n++;
                        } else if (n == 30) {
                            bal = rs2.getDouble("final_balance");
                            blnc.offer(bal);
                            connection.createStatement().executeUpdate("delete from transactionstatement_interest where account_number='" + acc_no + "' and tr_date_time='" + d1 + "';");
                            connection.createStatement().executeUpdate("insert into transactionstatement_interest values(0,'" + d + "',0,'Interest credit','" + acc_no + "','" + bal + "');");
                            ResultSet get_transaction_id=connection.createStatement().executeQuery("select*from transactionstatement_interest where account_number='"+acc_no+"' and tr_date_time='"+d+"';");
                            int tr_id=0;
                            if (get_transaction_id.next()) {
                                tr_id = get_transaction_id.getInt("transaction_id");
                            }
                            connection.createStatement().executeUpdate("update transactionstatement_interest set transaction_id=0 where transaction_id='"+tr_id+"';");

                        }
                    }


                    System.out.println(blnc);
                    Double last_balance=0d, final_balance,interest,tot_interest=0d;
                    while (!blnc.isEmpty()) {
                        last_balance=blnc.poll();
                        Double interest_pa=0.04d;
                        Double interest_pd=1/365d;
                        interest=(last_balance*interest_pa*interest_pd);
                        tot_interest =tot_interest+interest;
                    }
                    ResultSet latest_bal=connection.createStatement().executeQuery("select*from transactionstatement_interest where account_number='"+acc_no+"' order by transaction_id;");
                    while(latest_bal.next()){
                        last_balance=latest_bal.getDouble("final_balance");
                    }
                    final_balance=last_balance+tot_interest;
                    String trquery = "insert into transactionstatement (tr_date_time,tr_amount,tr_type,account_number, final_balance) values "
                            +"(?,?,?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(trquery);

                    java.util.Date today = new Date();
                    Timestamp tr_date_time = new Timestamp(today.getTime());
                    pst.setTimestamp(1, tr_date_time);
                    pst.setDouble(2, tot_interest);
                    pst.setString(3,"Interest Credit");
                    pst.setLong(4, acc_no);
                    pst.setDouble(5, final_balance);
                    pst.executeUpdate();

                    String trquery1 = "insert into transactionstatement_interest (tr_date_time,tr_amount,tr_type,account_number, final_balance) values "
                            +"(?,?,?,?,?)";
                    PreparedStatement pst1 = connection.prepareStatement(trquery1);
                    pst1.setTimestamp(1, tr_date_time);
                    pst1.setDouble(2, tot_interest);
                    pst1.setString(3,"Interest Credit");
                    pst1.setLong(4, acc_no);
                    pst1.setDouble(5, final_balance);
                    pst1.executeUpdate();
                    System.out.println(tot_interest);
                    duraDays=duraDays-30;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


