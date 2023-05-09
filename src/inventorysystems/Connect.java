/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystems;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        int check;
        
        if(checkInventoryName(subscriberID, inventoryName)) {
            JOptionPane.showMessageDialog(null, "Inventory exists!");
            return false;
        }
        
        try {
            stmt = conn.createStatement();
            sql = "SELECT restrictions from subscriber where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            rs2 = checkInventoryID();
            if(rs2 != null) count = rs2.getInt("inventory_id") + 1;
            while(rs.next()) {
            if(rs.getString("restrictions").equalsIgnoreCase("Premium Subscriber")) {
                sql = "INSERT INTO tblInventories VALUES("+count+", '"+inventoryName+"', "+subscriberID+")";
                stmt.executeUpdate(sql);
                //sql = "CREATE TABLE " + inventoryName + " (" +"itemID INT(3) PRIMARY KEY AUTO_INCREMENT," +"itemName VARCHAR(20) NOT NULL," +"description VARCHAR(20) NOT NULL," +"itemQuantity INT(3) NOT NULL," +"inventoryID INT(3) NOT NULL," +"isVisible TINYINT(1) NOT NULL" +")";
                //stmt.executeUpdate(sql);
                return true;
            } else {
                check = checkCapacity(subscriberID);
                if(check < 20) {
                    sql = "INSERT INTO tblInventories VALUES("+count+", '"+inventoryName+"', "+subscriberID+")";
                    stmt.executeUpdate(sql);
                    return true;
                } else JOptionPane.showMessageDialog(null, "Out of capacity!");
            }
            }
        } catch(SQLException s) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,s);
        }
        return false;
    }
    
    public ResultSet checkInventoryID() {
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
    
    public boolean checkInventoryName(int subscriberID, String inventoryName) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            sql = "select inventory_name from tblInventories where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            while(rs.next()) { 
                if(rs.getString("inventory_name").equalsIgnoreCase(inventoryName)) return true;
            }   
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return false;
    }
    
    public int checkCapacity(int subscriberID) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        int num = 0;
        try {
            stmt = conn.createStatement();
            sql = "select count(inventory_id) as numOfInventories from tblInventories where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            while(rs.next()) { 
                num = rs.getInt("numOfInventories");
            }   
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return num;
    }
    
    public ArrayList<Item> displayInventory(int inventoryID) {
        ArrayList<Item> acc = new ArrayList<Item>();
        String sql ="select itemID, itemName, itemDescription, itemQuantity, itemStatus from item where inventory_id = '"+inventoryID+"'";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Item a = new Item(rs.getInt("itemID"), rs.getString("itemName"), rs.getString("itemDescription"), rs.getInt("itemQuantity"), rs.getBoolean("itemStatus")); 
              acc.add(a);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return acc;
    }
    
    public int getInventoryID(int subscriberID, String inventoryName){
        Statement stmt;
        String sql;
        ResultSet rs = null;
        int num = 0;
        try {
            stmt = conn.createStatement();
            sql = "select inventory_id from tblInventories where subscriberID = "+subscriberID+" and inventory_name = '"+inventoryName+"'";
            rs = stmt.executeQuery(sql);
            while(rs.next()) { 
                num = rs.getInt("inventory_id");
            }   
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return num;
    }
}
