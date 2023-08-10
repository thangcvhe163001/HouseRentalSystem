/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DBContext {
    public Connection getConnection()throws Exception{
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber+"\\"+instance+";databaseName="+dbName;
        if((instance==null)||instance.trim().isEmpty())
            url = "jdbc:sqlserver://"+serverName+":"+portNumber+";databaseName="+dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
    private final String serverName = "DESKTOP-8V7R218\\SQLEXPRESS";
    private final String dbName = "SWP391_HouseRentalSystem";
    private final String portNumber = "1433";
    private final String instance = "";
    private final String userID = "sa";
    private final String password = "123456";
    
    
    public static void main(String[] args) {
        DBContext db = new DBContext();
        try {
            if(db.getConnection()== null){
                System.out.println("error");
            }else System.out.println("connect");
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
