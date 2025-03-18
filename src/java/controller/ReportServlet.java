/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dto.OrderView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author philo
 */
public class ReportServlet extends HttpServlet {
    
    private final String viewReport = "ReportPage.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String startDate = request.getParameter("startDate"); // Lấy startDate
        String endDate = request.getParameter("endDate"); // Lấy endDate
        System.out.println("startdate: "+startDate+" enddate: "+endDate);
        String url = viewReport;
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        try {
            List<OrderView> list = orderDao.getReport(startDate, endDate);
            int totalOrder = orderDao.getTotalOrderByOrderDate(startDate, endDate);
            double totalRevenu = orderDetailDao.getTotalRevenuByOrderDate(startDate, endDate);
            System.out.println("totalOrder: "+totalOrder+" totalRevenu: "+totalRevenu);
            request.setAttribute("totalorder", totalOrder);
            request.setAttribute("totalrevenu", totalRevenu);
            request.setAttribute("orderlist", list);
        } catch (SQLException e) {
            System.out.println("error: "+e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
