/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.OrderDetails;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author philo
 */
public class OrderDetailDAO {

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

    public boolean insert(OrderDetails o, Connection cnn) throws SQLException {

        PreparedStatement ps = null;
        try {

            String sql = "insert into [Order Details](ProductID,OrderID,UnitPrice,Quantity) values(?,?,?,?)";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, String.valueOf(o.getProductid()));
            ps.setString(2, String.valueOf(o.getOrderid()));
            ps.setString(3, String.valueOf(o.getUnitprice()));
            ps.setString(4, String.valueOf(o.getQuantity()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {

            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }

    public double getTotalRevenu() throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double total = 0;

        try {
            cnn = getConnection();
            String sql = "select sum(od.Quantity * od.UnitPrice) as total from [Order Details] od";
            ps = cnn.prepareStatement(sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error in getTotalRevenu: " + e.getMessage());
            e.printStackTrace();
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }

        }
        return total;
    }

    public double getTotalRevenuByOrderDate(String startDate, String endDate) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double total = 0;

        try {
            cnn = getConnection();
            String sql = "  select sum(od.Quantity * od.UnitPrice) as total from [Order Details] od \n"
                    + "  inner join Orders O ON  od.OrderID = o.OrderID\n"
                    + "  where o.OrderDate between ? and ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error in getTotalRevenu: " + e.getMessage());
            e.printStackTrace();
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }

        }
        return total;
    }
   
}
