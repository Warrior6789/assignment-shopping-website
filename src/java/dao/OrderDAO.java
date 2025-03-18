/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Order;
import dto.OrderView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philo
 */
public class OrderDAO {

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

    public int insert(Order o, Connection cnn) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        int orderId = 0;
        try {

            String sql = "insert into [Orders](CustomerID,OrderDate,RequiredDate,Freight,ShipAddress) values(?,?,?,?,?)";
            ps = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, String.valueOf(o.getCustomerid()));
            ps.setString(2, String.valueOf(o.getOrderdate()));
            ps.setString(3, String.valueOf(o.getRequiredate()));
            ps.setString(4, String.valueOf(o.getFreight()));
            ps.setString(5, o.getAddress());

            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
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
        return orderId;
    }

    public List<OrderView> getReport(String startdate, String enddate) throws SQLException {
        OrderView orderview = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderView> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = " SELECT\n"
                    + "    o.OrderID,\n"
                    + "    o.OrderDate,\n"
                    + "    c.ContactName,\n"
                    + "    c.Address,\n"
                    + "    COUNT(od.ProductID) AS TotalProducts,\n"
                    + "    SUM(od.Quantity * od.UnitPrice) AS TotalAmount\n"
                    + "FROM\n"
                    + "    Orders o\n"
                    + "JOIN\n"
                    + "    Customers c ON o.CustomerID = c.CustomerID\n"
                    + "JOIN\n"
                    + "    [Order Details] od ON o.OrderID = od.OrderID\n"
                    + "	WHERE o.OrderDate between ? and ?\n"
                    + "GROUP BY\n"
                    + "    o.OrderID, o.OrderDate, o.ShippedDate, c.ContactName, c.Address";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, startdate);
            ps.setString(2, enddate);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderdate = rs.getString(2);
                String contactname = rs.getString(3);
                String address = rs.getString(4);
                int totalProduct = Integer.parseInt(rs.getString(5));
                double total = Double.parseDouble(rs.getString(6));
                OrderView orderView = new OrderView(orderID, orderdate, contactname, address, totalProduct, total);
                list.add(orderView);
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
        return list;
    }

    public List<OrderView> getAll() throws SQLException {
        List<OrderView> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "SELECT\n"
                    + "    o.OrderID,\n"
                    + "    o.OrderDate,\n"
                    + "    c.ContactName,\n"
                    + "    o.[ShipAddress],\n"
                    + "    COUNT(od.ProductID) AS TotalProducts,\n"
                    + "    SUM(od.Quantity * od.UnitPrice) AS TotalAmount\n"
                    + "FROM\n"
                    + "    Orders o\n"
                    + "JOIN\n"
                    + "    Customers c ON o.CustomerID = c.CustomerID\n"
                    + "JOIN\n"
                    + "    [Order Details] od ON o.OrderID = od.OrderID\n"
                    + "GROUP BY\n"
                    + "    o.OrderID, o.OrderDate, o.ShippedDate, c.ContactName, o.[ShipAddress]";
            ps = cnn.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String date = rs.getString(2);
                String contactname = rs.getString(3);
                String address = rs.getString(4);
                int totalProduct = Integer.parseInt(rs.getString(5));
                double total = Double.parseDouble(rs.getString(6));
                OrderView orderView = new OrderView(id, date, contactname, address, totalProduct, total);
                list.add(orderView);
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
        return list;
    }

    public int getTotalOrder() throws SQLException {
        int total = 0;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "  select count(*)as total from Orders";
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
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
        return total;
    }

    public int getTotalOrderByOrderDate(String startDate, String endDate) throws SQLException {
        int total = 0;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "  select count(*)as total from Orders o where o.OrderDate between ? and ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
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
        return total;
    }
}
