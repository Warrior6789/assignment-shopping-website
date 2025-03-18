/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dto.CartItem;
import dto.Customer;
import dto.CustomerErr;
import dto.Order;
import dto.OrderDetails;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.tomcat.util.digester.Digester;

/**
 *
 * @author philo
 */
public class PayServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("======================= IN PAYMENT SERVLET =======================");
        HttpSession sessionCart = request.getSession();
        CustomerDAO customerDao = new CustomerDAO();
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        CartDAO cartDao = new CartDAO();
        Connection cnn = OrderDAO.getConnection();

        cnn.setAutoCommit(false);
        boolean isError = false;
        CustomerErr customerErr = new CustomerErr();
        int customerId = -1;
        String contactName = request.getParameter("contactname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        double freight = Double.parseDouble(request.getParameter("freight"));
        String orderDate = request.getParameter("orderdate");
        double total = Double.parseDouble(request.getParameter("total"));

        if (contactName == null || contactName.trim().isEmpty()) {
            isError = true;
            customerErr.setContactName("Contact Name cannot empty.");
        }
        if (address == null || address.trim().isEmpty()) {
            isError = true;
            customerErr.setAddress("Address cannot empty.");
        }
        if (phone == null || phone.trim().isEmpty()) {
            isError = true;
            customerErr.setPhone("Phone cannot empty.");
        }

        Customer customer = new Customer(contactName, address, phone);
        try {
            // check customer
            if (isError == false) {
                Customer exisCustomer = customerDao.getByPhoneAndName(customer.getPhone(), customer.getContactName());

                if (exisCustomer == null) {
                    customerId = customerDao.insert(cnn, customer);
                } else {
                    customerId = exisCustomer.getId();
                }
            }
            System.out.println("customer id: " + customerId);
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            Date inputDate = inputFormat.parse(orderDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String outputDate = outputFormat.format(inputDate);

            java.sql.Date sqlOrderDate = java.sql.Date.valueOf(outputDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(sqlOrderDate);
            cal.add(Calendar.DATE, 2);
            java.sql.Date sqlRequireDate = new java.sql.Date(cal.getTimeInMillis());
            if (customerId == -1) {
                throw new Exception("Customer ID was not initialized.");
            }
            Order order = new Order(customerId, sqlOrderDate, sqlRequireDate, freight, address);
            int orderId = orderDao.insert(order, cnn);
            System.out.println("orderid: " + orderId);
            if (orderId <= 0) {
                throw new Exception("Can not create Order!");
            }

            HashMap<Integer, CartItem> list = (HashMap<Integer, CartItem>) sessionCart.getAttribute("cart");
            if (list == null) {
                Cookie cookie = cartDao.getCookieByName(request, "cart");
                if (cookie == null) {
                    System.out.println("cart is null");
                } else {
                    list = cartDao.getCartFromCookie(cookie);
                }
            }

            List<CartItem> listcart = new ArrayList<>(list.values());

            for (CartItem cartItem : listcart) {
                OrderDetails o = new OrderDetails(cartItem.getProduct().getProductId(), orderId, cartItem.getProduct().getUnitPrice(), cartItem.getQuantity());
                System.out.println("order details id: " + o.getOrderid());
                orderDetailDao.insert(o, cnn);
            }

            cnn.commit();
            String vnp_IpAddr = request.getHeader("X-Forwarded-For");
            if (vnp_IpAddr == null || vnp_IpAddr.isEmpty()) {
                vnp_IpAddr = request.getRemoteAddr();
            }
            String vnpUrl = createVNPayUrl(contactName, vnp_IpAddr, phone, total, orderId);

            
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
            sessionCart.removeAttribute("cart");
            response.sendRedirect(vnpUrl);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            if (cnn != null && !cnn.isClosed()) {
                cnn.rollback();
            }
            request.setAttribute("error", customerErr);
            request.getRequestDispatcher("ViewCartPageServlet").forward(request, response);
        } finally {
            cnn.close();
        }

    }

    private String createVNPayUrl(String contactName, String vnp_IpAddr, String Phone, double totalAmount, int orderId) {
        // create vnpay payment url 
        String vnp_TmnCode = "L1HPI0RD";
        String vnp_HashSecret = "672419CMVBE0WQETJG3W0XHPOVK7DMD3";
        String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

        HashMap<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf((int) (totalAmount * 100)));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", String.valueOf(orderId));
        vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/Assignment_ShoppingWebSite/VNPAYReturnServlet");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_OrderType", "1");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cal.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cal.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cal.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        try {
            String orderInfo = URLEncoder.encode("Order" + orderId + "from" + contactName, "UTF-8");
            System.out.println("OrderInfo (mã hóa): " + orderInfo);
            vnp_Params.put("vnp_OrderInfo", orderInfo);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  // Log lỗi nếu có
        }

        StringBuilder hashData = new StringBuilder();
        List<String> fieldsName = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldsName);

        for (String field : fieldsName) {
            String fieldValue = vnp_Params.get(field);
            if (fieldValue != null && !fieldValue.isEmpty()) {

                try {
                    hashData.append(field).append("=").append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())).append("&");
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(PayServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        String queryString = hashData.toString();
        queryString = queryString.substring(0, queryString.length() - 1);
        System.out.println("queryString in pay: "+queryString);
       

        String vnp_SecureHash = HmacUtils.hmacSha512Hex(vnp_HashSecret, queryString);

   

        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);

        StringBuilder vnpUrl = new StringBuilder(vnp_PayUrl);
        vnpUrl.append("?").append(queryString);
        vnpUrl.append("&vnp_SecureHash=").append(vnp_SecureHash);



        return vnpUrl.toString();
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
