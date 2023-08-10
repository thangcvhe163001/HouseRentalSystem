/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Account_Role;
import entity.House;
import entity.Messages;
import entity.Messengers;
import entity.Secure_Answers;
import entity.Secure_Questions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author win
 */
public class DAOAccount extends DBContext {
    
    public static String getCode(){
        Random rdn = new Random();
        int code = rdn.nextInt(999999);
        return String.valueOf(code);
    }
    public void sendVerificationEmail(String to, final String user, final String pass) {
        int id = getIdByAccount(to);
        String sub = "Email verification.";
        String msg = "<!DOCTYPE html>\n"
                +"<html lang=\"en\">\n"
                +"\n"
                +"<head>\n"
                +"\n"
                +"<body>\n"
                +"<h3 style=\"color: blue;\">You need to verify your email using the link below: </h3>\n"
                +"<a href=\"http://localhost:8080/SWP391_HouseRentalSystem/login?id="+id+"\">"+"http://localhost:8080/SWP391_HouseRentalSystem/login?id="+id+"</a>"
                +"</body>\n"
                +"\n"
                +"</html>";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openAccount(int id){
        try {
            String stmSql = "Update Account set Status = 1 where Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int getIdByAccount(String email){
        try {
            String stmSql = "select Id from Account where Email = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Account Login(String email, String password) {
        String sql = "SELECT * FROM Account WHERE Email = ? and Password = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getString(12));
                
                List<House> list = new ArrayList<>();
                DAOHouse dh = new DAOHouse();

                String xSQL = "Select * from Favourite_House where [User_ID] = ?";
                PreparedStatement ptm = conn.prepareStatement(xSQL);
                ptm.setInt(1, rs.getInt(1));
                ResultSet resultSet = ptm.executeQuery();
                
                while (resultSet.next()) {
                    list.add(dh.getHouseById(resultSet.getInt(3)));

                }
                a.setFavourites(list);
                
                List<House> listt = new ArrayList<>();
                DAOHouse dhh = new DAOHouse();

                String xSQLx = "Select * from History_House where Account_ID = ?";
                PreparedStatement ptmm = conn.prepareStatement(xSQLx);
                ptmm.setInt(1, rs.getInt(1));
                ResultSet resultSett = ptmm.executeQuery();
                while (resultSett.next()) {
                    listt.add(dhh.getHouseById(resultSett.getInt(3)));
                }
                a.setHistory(listt);
                
                return a;
            }
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Account getAccount(String email, String password) {
        try {
            String stmSql = "select * from Account where Email = ? and Password = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getString(12));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountById(int id) {
        try {
            String stmSql = "Select * from Account where id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getString(12));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void changeInformation(String fullName, String address, String phone, Boolean gender, String picture, String email) {
        try {
            String stmSql = "update Account set Fullname = ? , Address = ? , Phone_Number = ? , Gender = ? , Profile_Picture = ? "
                    + " where Email = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, fullName);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setBoolean(4, gender);
            ps.setString(5, picture);
            ps.setString(6, email);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changePassword(String email, String password) {
        try {
            String stmSql = "update Account set Password = ? where Email = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(2, email);
            ps.setString(1, password);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changePhone(String email, String phone) {
        try {
            String stmSql = "update Account set Phone_Number = ? where Email  = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(2, email);
            ps.setString(1, phone);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkEmail(String email) {
        try {
            String stmSql = "select * from Account where Email = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Have error!");
        }
        return false;
    }

    public void addAccount(String fullname, boolean gender, String address, String email, String password, String phone, int roleId, int questionId, int answerId, String picture) {
        try {
            String stmSql = "insert into Account([Fullname]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Address]\n"
                    + "      ,[Email]\n"
                    + "      ,[Password]\n"
                    + "      ,[Phone_Number]\n"
                    + "      ,[Role_ID]\n"
                    + "      ,[Status]\n"
                    + "      ,[Secure_Question_ID]\n"
                    + "      ,[Secure_Answer_ID]\n"
                    + "      ,[Profile_Picture]) values (?,?,?,?,?,?,?,?,?,?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, fullname);
            ps.setBoolean(2, gender);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, phone);
            ps.setInt(7, roleId);
            ps.setBoolean(8, false);
            ps.setInt(9, questionId);
            ps.setInt(10, answerId);
            ps.setString(11, picture);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Have error!");
        }
    }

    public boolean checkExistPassword(String email, String pass) {
        try {
            String stmSql = "select * from Account where Email = ? and Password = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Messages> getListMessages(int senderId, int receiverId) {
        try {
            String stmSql = "select * from Messages where (Sender_ID = ? and Receiver_ID = ?) or (Sender_ID = ? and Receiver_ID = ?)";
            List<Messages> lsM = new ArrayList<>();
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, senderId);
            ps.setInt(4, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, receiverId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Messages m = new Messages(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8));
                lsM.add(m);
            }
            System.out.println(lsM.size() + "!");
            return lsM;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean checkSecure(String email, int idQ, int idA) {
        try {
            String stmSql = "select * from Account where Email = ? and Secure_Question_ID = ? and Secure_Answer_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, email);
            ps.setInt(2, idQ);
            ps.setInt(3, idA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Account_Role> getListRole() {
        try {
            List<Account_Role> lsR = new ArrayList<>();
            String stmSql = "select * from Account_Role where Id != 1";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account_Role r = new Account_Role(rs.getInt(1), rs.getString(2));
                lsR.add(r);
            }
            return lsR;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Secure_Questions> getListQuestion() {
        try {
            List<Secure_Questions> lsQ = new ArrayList<>();
            String stmSql = "select * from Secure_Questions";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Secure_Questions q = new Secure_Questions(rs.getInt(1), rs.getString(2));
                lsQ.add(q);
            }
            return lsQ;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Secure_Answers> getListAnswer(int questionId) {
        try {
            List<Secure_Answers> lsA = new ArrayList<>();
            String stmSql = "select * from Secure_Answers where Secure_Question_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Secure_Answers q = new Secure_Answers(rs.getInt(1), rs.getInt(2), rs.getString(3));
                lsA.add(q);
            }
            return lsA;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Messengers> getListMessengers(int Receiver_ID,String name) {
        try {
            List<Messengers> lsMgr = new ArrayList<>();
            String stmSql = "select m.Sender_ID,a.Fullname,m.Sent_Date,m.Content,a.Profile_Picture from Messages as m inner join Account as a on m.Sender_ID = a.Id where m.Receiver_ID = ? and a.Fullname like ? and m.id in (select max(Id) from Messages where Receiver_ID = ? group by Sender_ID,Receiver_ID)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, Receiver_ID);
            ps.setString(2, "%"+name+"%");
            ps.setInt(3, Receiver_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Messengers m = new Messengers(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
                lsMgr.add(m);
            }
            return lsMgr;
        } catch (Exception e) {
            System.out.println("Have error in getListMessengers!");
        }
        return null;
    }
     public int getNewMessgageId(int senderId, int receiverId){
        try {
            String stmSql = "select max(Id) from Messages where (Sender_ID = ? and Receiver_ID = ?) or (Sender_ID = ? and Receiver_ID = ?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, senderId);
            ps.setInt(4, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, receiverId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public void insertMessages(int senderId, int receiverId, String inbox) {
        try {
            if (senderId == receiverId) {
                return;
            }
            String stmSql = "insert into Messages(Sender_ID,Receiver_ID,Sent_Date,Content,Status,Deleted_By_Sender,Deleted_By_Receiver) values(?,?,?,?,?,?,?)";
            Date now = new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = f.format(now);
            java.sql.Date date2 = java.sql.Date.valueOf(date1);
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setDate(3, date2);
            ps.setString(4, inbox);
            ps.setBoolean(5, false);
            ps.setBoolean(6, false);
            ps.setBoolean(7, false);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteMessage(int id, int senderId, int account) {
        try {
            String stmSql = "update Messages";
            if(senderId == account){
                stmSql += " set Deleted_By_Sender = ? where Id = ?";
            }else{
                stmSql += " set Deleted_By_Receiver = ? where Id = ?";
            }
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(2, id);
            ps.setBoolean(1, true);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        DAOAccount dao = new DAOAccount();
        Account acc = dao.getAccountById(12);
        System.out.println(acc);
    }
    ////test

}
