/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProductViewDTO;
import dto.Products;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philo
 */
public class ProductDAO {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;database=Shop;encrypt=false;trustServerCertificate=true;characterEncoding=UTF-8\";";
            Connection cnn = DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    public List<ProductViewDTO> getAll() throws SQLException {
        List<ProductViewDTO> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select ProductID, ProductName,UnitPrice,ProductImage,CategoryName,Description,s.CompanyName,p.QuantityPerUnit from Products p inner join Categories c on p.CategoryID = c.CategoryID inner join Suppliers s on p.SupplierID = s.SupplierID\n";
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                double price = Double.parseDouble(rs.getString(3));
                String description = rs.getString(6);
                String image = rs.getString(4);
                String categoryName = rs.getString(5);
                String supplierName = rs.getString(7);
                String QuantityPerUnit = rs.getString(8);
                ProductViewDTO p = new ProductViewDTO(id, name, price, image, description, categoryName, supplierName, QuantityPerUnit);
               
                list.add(p);
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

    public boolean delete(String id) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "delete from Products where ProductID=?";
            ps = cnn.prepareCall(sql);
            ps.setString(1, id);
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

    public boolean insert(Products p) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        try {
            cnn = getConnection();
            String sql = "insert into Products values(?,?,?,?,?,?)";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, String.valueOf(p.getSupplierId()));
            ps.setString(3, String.valueOf(p.getCategoryId()));
            ps.setString(4, p.getQuantityPerUnit());
            ps.setString(5, String.valueOf(p.getUnitPrice()));
            ps.setString(6, p.getImage());
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

    public ProductViewDTO getByID(String value) throws SQLException {
        ProductViewDTO p = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select ProductID, ProductName,UnitPrice,ProductImage,CategoryName,Description,s.CompanyName,QuantityPerUnit from Products p inner join Categories c on p.CategoryID = c.CategoryID inner join Suppliers s on p.SupplierID = s.SupplierID\n"
                    + "                   where p.ProductID =?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                double price = Double.parseDouble(rs.getString(3));
                String image = rs.getString(4);
                String catename = rs.getString(5);
                String desc = rs.getString(6);
                String suppliername = rs.getString(7);
                String QuantityPerUnit = rs.getString(8);
                p = new ProductViewDTO(id, name, price, image, desc, catename, suppliername, QuantityPerUnit);

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
            return p;
        }
    }

    public Products getProductById(String value) throws SQLException {
        Products p = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select * from Products where id =?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                int supid = Integer.parseInt(rs.getString(3));
                int cateid = Integer.parseInt(rs.getString(4));
                String quantity = rs.getString(5);
                double price = Double.parseDouble(rs.getString(6));
                String image = rs.getString(7);
                p = new Products(supid, name, supid, cateid, quantity, price, image);
                System.out.println("product: " + p);
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
            return p;
        }
    }

    public boolean update(Products p) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "UPDATE Products SET ProductName = ?, SupplierID = ?, CategoryID = ?, QuantityPerUnit = ?, UnitPrice = ?, ProductImage = ? WHERE ProductID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, String.valueOf(p.getSupplierId()));
            ps.setString(3, String.valueOf(p.getCategoryId()));
            ps.setString(4, String.valueOf(p.getQuantityPerUnit()));
            ps.setString(5, String.valueOf(p.getUnitPrice()));
            ps.setString(6, String.valueOf(p.getImage()));
            ps.setString(7, String.valueOf(p.getProductId()));
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

    public List<ProductViewDTO> searchByIdOrNameOrUnitPrice(String value) throws SQLException {
        ProductViewDTO p = null;
        List<ProductViewDTO> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("value to search: "+value);
        try {
            cnn = getConnection();
            String sql =  "SELECT ProductID, ProductName, UnitPrice, ProductImage, CategoryName, Description, " +
                     "s.CompanyName, QuantityPerUnit " +
                     "FROM Products p " +
                     "INNER JOIN Categories c ON p.CategoryID = c.CategoryID " +
                     "INNER JOIN Suppliers s ON p.SupplierID = s.SupplierID " +
                     "WHERE (TRY_CONVERT(INT, ?) IS NOT NULL AND ProductID = TRY_CONVERT(INT, ?)) " +
                     "OR (LOWER(ProductName) LIKE LOWER(?)) " +
                     "OR (TRY_CONVERT(DECIMAL(10,2), ?) IS NOT NULL AND UnitPrice = TRY_CONVERT(DECIMAL(10,2), ?))";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, value);
            ps.setString(2, value);
            ps.setString(3,"%"+value.toLowerCase()+"%");
            ps.setString(4, value);
             ps.setString(5, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                double price = Double.parseDouble(rs.getString(3));
                String image = rs.getString(4);
                String catename = rs.getString(5);
                String desc = rs.getString(6);
                String suppliername = rs.getString(7);
                String QuantityPerUnit = rs.getString(8);
                p = new ProductViewDTO(id, name, price, image, desc, catename, suppliername, QuantityPerUnit);
                list.add(p);
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
            return list;
        }
    }
   
}
