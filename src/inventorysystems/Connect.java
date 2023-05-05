/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystems;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daugd
 */
public class Connect {
    
    Connection conn = null;
    public Connect() {
        try {
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinventory", "root", "");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinventorysystem", "root", "");
        } catch(SQLException s) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, s);
        }
    }
    
    public ResultSet checkSubscriber(int subscriberID) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            sql = "SELECT * from subscriber where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            if(rs.next()) return rs;
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return null;
    }
    
    public boolean createInventory(int subscriberID, String inventoryName) {
        Statement stmt;
        String sql;
        ResultSet rs = null, rs2 = null;
        int count = 1;
        try {
            stmt = conn.createStatement();
            sql = "SELECT restrictions from subscriber where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                rs2 = checkInventory();
                if(rs2 != null) count = rs2.getInt("inventory_id") + 1;
                sql = "INSERT INTO tblInventories VALUES("+count+", '"+inventoryName+"', '"+rs.getString("restrictions")+"', "+subscriberID+")";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE " + inventoryName + " (" +"itemID INT(3) PRIMARY KEY AUTO_INCREMENT," +"itemName VARCHAR(20) NOT NULL," +"description VARCHAR(20) NOT NULL," +"itemQuantity INT(3) NOT NULL," +"inventoryID INT(3) NOT NULL," +"isVisible TINYINT(1) NOT NULL" +")";
                stmt.executeUpdate(sql);
                return true;
            }
        } catch(SQLException s) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,s);
        }
        return false;
    }
    
    public ResultSet checkInventory() {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            sql = "SELECT inventory_id from tblInventories order by inventory_id DESC";
            rs = stmt.executeQuery(sql);
            if(rs.next()) return rs;
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return null;
    }
}
