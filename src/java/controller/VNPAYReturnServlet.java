/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.HmacUtils;

/**
 *
 * @author philo
 */
public class VNPAYReturnServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String vnp_TmnCode = request.getParameter("vnp_TmnCode");
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_BankCode = request.getParameter("vnp_BankCode");
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        String vnp_vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        double totalAmount = Double.parseDouble(vnp_Amount) / 100;
        Map<String, String[]> parameterMap = request.getParameterMap();

        StringBuilder hashData = new StringBuilder();

        List<String> fieldsName = new ArrayList<>(parameterMap.keySet());
        Collections.sort(fieldsName);

        for (String field : fieldsName) {
            String fieldValue = request.getParameter(field);

            if (fieldValue != null && !fieldValue.isEmpty() && !field.equals("vnp_SecureHash") && !field.equals("vnp_SecureHashType")) {
                hashData.append(field).append("=").append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())).append("&");
            }
        }

        String queryString = hashData.toString().trim();

        if (queryString.endsWith("&")) {
            queryString = queryString.substring(0, queryString.length() - 1);
        }

        String mess = null;
        String vnp_HashSecret = "672419CMVBE0WQETJG3W0XHPOVK7DMD3";
        String vnp_SecureHashVerify = HmacUtils.hmacSha512Hex(vnp_HashSecret, queryString);
        if (vnp_SecureHash.equals(vnp_SecureHashVerify)) {
            if ("00".equals(vnp_ResponseCode)) {
                mess = "Giao dịch thành công!";
            } else {
                mess = "Giao dịch không thành công!";
            }
        } else {
            mess = "Mã bảo mật không hợp lệ!";
        }

        request.setAttribute("message", mess);
        request.setAttribute("orderId", vnp_TxnRef);
        request.setAttribute("amount", String.valueOf(totalAmount));

        request.getRequestDispatcher("paymentResult.jsp").forward(request, response);
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
