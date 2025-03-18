/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author philo
 */
public class AccountDAO {
    
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;database=Shop";
            Connection cnn = DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    
    public Account login(String username, String password) throws Exception {
        Account acc = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection(); // tạo connection 
           
            String sql = "select * from Account where UserName=? and Password=?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String pass = rs.getString(3);
                String fullname = rs.getString(4);
                int type = Integer.parseInt(rs.getString(5));
                acc = new Account(id, username, password, fullname, type);
             
            }
            
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return acc;
    }
    
    public boolean register(Account a) throws SQLException {
   
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection(); // tạo connection 
            String sql = "INSERT INTO Account (UserName, Password, FullName, Type) \n"
                    + "VALUES (?,?,?,?);";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, a.getUserName());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getFullName());
            ps.setString(4, String.valueOf("2"));
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public boolean update(Account acc) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "UPDATE [Account] SET [UserName] = ?, [Password] = ?, [FullName] = ? WHERE [AccountID] = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, acc.getUserName());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getFullName());
            ps.setString(4, String.valueOf(acc.getId()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }
    public Account getByID(String value) throws SQLException {
      
        Account acc = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select * from [Account] where [AccountID] = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String username = rs.getString(2);
                String password = rs.getString(3);
                String fullname = rs.getString(4);
                int type = Integer.parseInt(rs.getString(5));
                acc = new Account(id, username, password, fullname, type);
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            return acc;
        }
    }
    public Account getByName(String value) throws SQLException {
        Account acc = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select * from Account where UserName = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("UserName");
                String password = rs.getString("Password");
                String fullname = rs.getString("FullName");
                int type = Integer.parseInt(rs.getString("Type"));
                acc = new Account(username, password, fullname, type);
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
          return acc;
    }
    
}
