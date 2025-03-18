/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.SuppliersDAO;
import dto.Categories;
import dto.ProductErr;
import dto.ProductViewDTO;
import dto.Products;
import dto.Supplier;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileUpload;

/**
 *
 * @author philo
 */
public class UpdateServlet extends HttpServlet {

    private final String homePage = "ViewHomePageServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("===========================UPDATESERVLET===========================");
        System.out.println("Request Encoding: " + request.getCharacterEncoding());
        String mess = null;
        String url = homePage;
        ProductDAO pDao = new ProductDAO();
        boolean isError = false;
        try {
            if (ServletFileUpload.isMultipartContent(request)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload fileUpload = new ServletFileUpload(factory);
                List<FileItem> items = fileUpload.parseRequest(request);

                int id = 0;
                String productName = null;
                String priceStr = null;
                int supplierId = 0;
                int categoryId = 0;
                String quantityStr = null;
                String photoFileName = null;
                Products p;
                ProductErr productErr = new ProductErr();

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");
                        if ("productname".equals(fieldName)) {
                            productName = fieldValue;
                        } else if ("id".equals(fieldName)) {
                            id = Integer.parseInt(fieldValue);
                        } else if ("productprice".equals(fieldName)) {
                            priceStr = fieldValue;
                        } else if ("supplierid".equals(fieldName)) {
                            supplierId = Integer.parseInt(fieldValue);
                        } else if ("categoryid".equals(fieldName)) {
                            categoryId = Integer.parseInt(fieldValue);
                        } else if ("quantity".equals(fieldName)) {
                            quantityStr = fieldValue;
                        }

                    } else if (item.getFieldName().equals("photo")) {
                        // Xử lý ảnh mới khi người dùng tải lên
                        String fileName = new File(item.getName()).getName();
                        if (!fileName.isEmpty()) {
                            photoFileName = "images/" + fileName;  // Gắn thư mục images vào tên ảnh
                            String uploadPath = getServletContext().getRealPath("/images");
                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdir();  // Tạo thư mục nếu không tồn tại
                            }
                            File uploadFile = new File(uploadPath + File.separator + fileName);
                            try {
                                item.write(uploadFile);  // Lưu ảnh vào thư mục
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // Nếu không có ảnh mới, lấy ảnh cũ từ cơ sở dữ liệu
                if (photoFileName == null || photoFileName.isEmpty()) {
                    ProductViewDTO product = pDao.getByID(String.valueOf(id));
                    if (product != null) {
                        photoFileName = product.getProductImage();  // Lấy ảnh cũ từ cơ sở dữ liệu
                    }
                }

                // Kiểm tra lỗi đầu vào của các trường
                if (productName == null || productName.trim().isEmpty()) {
                    isError = true;
                    productErr.setProductName("Product Name cannot empty.");
                }
                if (priceStr == null || priceStr.trim().isEmpty()) {
                    isError = true;
                    productErr.setUnitPrice("Price Name cannot empty.");
                }
                if (quantityStr == null || quantityStr.trim().isEmpty()) {
                    isError = true;
                    productErr.setQuantityPerUnit("Quantity cannot empty.");
                }

                double price = 0;
                try {
                    price = Double.parseDouble(priceStr);
                    if (price <= 0) {
                        isError = true;
                        productErr.setUnitPrice("Price must be greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    productErr.setUnitPrice("Price must be a valid number.");
                    isError = true;
                }

                // Kiểm tra lỗi và cập nhật sản phẩm nếu không có lỗi
                if (!isError) {
                    p = new Products(id, productName, supplierId, categoryId, quantityStr, price, photoFileName);
                    boolean success = pDao.update(p);
                    if (success) {
                        mess = "update success";
                    }
                }

            } else {
                System.out.println("not a multipart request");
            }

        } catch (Exception e) {
            System.out.println("error in update : " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.setAttribute("mess", mess);
            RequestDispatcher rd = request.getRequestDispatcher(homePage);
            rd.forward(request, response);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
