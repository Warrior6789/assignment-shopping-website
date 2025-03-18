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
import dto.CustomerErr;
import dto.ProductErr;
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

/**
 *
 * @author philo
 */
public class CreateServlet extends HttpServlet {

    private final String viewHomePage = "ViewHomePageServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = viewHomePage;
        ProductDAO productDao = new ProductDAO();
        boolean isError = false;
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                // kiểm tra request có phải yêu cầu tải tệp lên k
                // kiểm tra http có phải là multipart/form-data k
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload fileUpload = new ServletFileUpload(factory);
                List<FileItem> items = fileUpload.parseRequest(request);
                // diskfileitemfactory đối tượng này tạo fileitem
                // servletfileUpload đối tượng này xử lý yêu cầu tải lên 
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
                        String fieldValue = item.getString();
                        System.out.println("Processing field: " + fieldName + " -> " + fieldValue);
                        if ("productname".equals(fieldName)) {
                            productName = fieldValue;
                        } else if ("price".equals(fieldName)) {
                            priceStr = fieldValue;
                        } else if ("supplierid".equals(fieldName)) {
                            supplierId = Integer.parseInt(fieldValue);
                        } else if ("categoryid".equals(fieldName)) {
                            categoryId = Integer.parseInt(fieldValue);
                        } else if ("quantity".equals(fieldName)) {
                            quantityStr = fieldValue;
                        }

                    } else if (item.getFieldName().equals("photo")) {
                        File file = new File(item.getName());
                        photoFileName = "images/" + file.getName();
                        String uploadPath = getServletContext().getRealPath("/images");
                        System.out.println("uploadPath: " + uploadPath);
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }
                        File uploadFile = new File(uploadPath + File.separator + file.getName());
                        try {
                            item.write(uploadFile);
                            System.out.println("File saved successfully.");
                        } catch (Exception e) {
                            System.out.println("Error saving file: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }

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

                // check
                if (isError == true) {
                    SuppliersDAO supDao = new SuppliersDAO();
                    CategoryDAO cateDao = new CategoryDAO();
                    List<Supplier> listSup = supDao.getAll();
                    List<Categories> listCate = cateDao.getAll();
                    request.setAttribute("error", productErr);
                    request.setAttribute("supList", listSup);
                    request.setAttribute("cateList", listCate);
                    System.out.println("Error: " + productErr.toString());
                    request.getRequestDispatcher("CreatePage.jsp").forward(request, response);
                    return;
                    
                } else {
                    p = new Products(productName, price, supplierId, categoryId, quantityStr, photoFileName);
                    if (productDao.insert(p)) {
                        System.out.println("insert success");
                    } else {
                        System.out.println("insert fail");
                    }
                    System.out.println(p.toString());
                }

            } else {
                System.out.println("not a multipart request");
            }
            RequestDispatcher rd = request.getRequestDispatcher(viewHomePage);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("error in create : " + e.getMessage());
            e.printStackTrace();
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
