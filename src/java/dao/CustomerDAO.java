/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author philo
 */
public class CustomerDAO {

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

    public Customer getByPhoneAndName(String phone, String name) throws SQLException {
        Customer customer = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select CustomerID,ContactName,Address,Phone from Customers where Phone = ? and ContactName = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, String.valueOf(phone));
            ps.setString(2, String.valueOf(name));
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String contactName = rs.getString(2);
                String address = rs.getString(3);
                String customerPhone = rs.getString(4);
                customer = new Customer(id, contactName, address, phone);
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
        return customer;
    }

    public int insert(Connection cnn, Customer c) throws SQLException {

        PreparedStatement ps = null;
        int customerid = 0;
        ResultSet rs = null;
        try {

            String sql = "insert into Customers(ContactName,Address,Phone) values(?,?,?)";
            ps = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, c.getContactName());
            ps.setString(2, c.getAddress());
            ps.setString(3, c.getPhone());

            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    customerid = rs.getInt(1);
                    System.out.println("customerid: " + customerid);
                }
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return customerid;
    }

}
