/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Categories;
import dto.ProductViewDTO;
import dto.Supplier;
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
public class CategoryDAO {

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

    public List<Categories> getAll() throws SQLException {
        List<Categories> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select * from Categories ";
            ps = cnn.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String Desc = rs.getString(3);
                Categories c = new Categories(id, name, Desc);
                list.add(c);
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

    public Categories getByID(String value) throws SQLException {
        Categories c = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "select * from Categories where CategoryID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String desc = rs.getString(3);
                c = new Categories(id, name, desc);
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
            return c;
        }
    }

    public List<ProductViewDTO> getProductByCategoryID(String value) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductViewDTO p = null;
        List<ProductViewDTO> list = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "select ProductID,ProductName,UnitPrice,ProductImage,\n"
                    + "Description,CategoryName,CompanyName,QuantityPerUnit \n"
                    + "\n"
                    + "from Products p inner join Categories c on p.CategoryID = c.CategoryID inner join \n"
                    + "Suppliers s on s.SupplierID = p.SupplierID where c.CategoryID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productid = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                double price = Double.parseDouble(rs.getString(3));
                String img = rs.getString(4);
                String desc = rs.getString(5);
                String cateName = rs.getString(6);
                String supplierName = rs.getString(7);
                String quantity = rs.getString(8);
                p = new ProductViewDTO(productid, name, price, img, desc, cateName, supplierName, quantity);
                 System.out.println("p: "+p.toString());
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

    public boolean insert(Categories c) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        try {
            cnn = getConnection();
            String sql = "insert into Categories values(?,?)";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
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

    public boolean update(Categories c) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "update Categories set CategoryName = ?, Description =? where CategoryID =?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            ps.setString(3, String.valueOf(c.getId()));
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

    public boolean delete(String id) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "delete from Categories where CategoryID=?";
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
}
