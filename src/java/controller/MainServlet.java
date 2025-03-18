/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author philo
 */
public class MainServlet extends HttpServlet {

    private final String homePage = "HomePage.jsp";
    private final String loginPage = "LoginPage.jsp";
    private final String loginServlet = "LoginServlet";
    private final String createServlet = "CreateServlet";
    private final String searchServlet = "SearchServlet";
    private final String editServlet = "EditServlet";
    private final String detailServlet = "DetailServlet";
    private final String deleteServlet = "DeleteServlet";
    private final String viewCategoryPage = "ViewCategoryPageServlet";
    private final String registerServlet = "RegisterServlet";
    private final String viewCreatePage = "ViewCreatePageServlet";
    private final String createCateServlet = "CreateCategoryServlet";
    private final String viewUpdateCatePage = "ViewUpdateCategoryPageServlet";
    private final String deleteCateServlet = "DeleteCategoryServlet";
    private final String userSearchServlet = "UserSearchServlet";
    private final String updateCategoryServlet = "UpdateCategoryServlet";
    private final String updateProfile = "UpdateProfileServlet";
    private final String addToCart = "AddToCartServlet";
    private final String PayServlet = "PayServlet";
    private final String filterServlet = "FilterServlet";
    private final String viewReportServlet = "ViewReportPageServlet";
    private final String reportServlet = "ReportServlet";
    private final String updateProduct = "UpdateServlet";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        System.out.println("action: " + action);
        String url = loginPage;
        try {
            if ("login".equals(action)) {
                url = loginServlet;
            } else if ("create".equals(action)) {
                url = createServlet;
            } else if ("edit".equals(action)) {
                url = editServlet;
            } else if ("detail".equals(action)) {
                url = detailServlet;
            } else if ("delete".equals(action)) {
                url = deleteServlet;
            } else if ("viewcreatepage".equals(action)) {
                url = viewCreatePage;
            } else if ("search".equals(action)) {
                url = searchServlet;
            } else if ("register".equals(action)) {
                url = registerServlet;
            } else if ("viewcate".equals(action)) {
                url = viewCategoryPage;
            } else if ("createCate".equals(action)) {
                url = createCateServlet;
            } else if ("viewupdatecate".equals(action)) {
                url = viewUpdateCatePage;
            } else if ("deletecate".equals(action)) {
                url = deleteCateServlet;
            } else if ("updatecate".equals(action)) {
                url = updateCategoryServlet;
            } else if ("save".equals(action)) {
                url = updateProfile;
            } else if ("usersearch".equals(action)) {
                url = userSearchServlet;
            } else if ("addtocart".equals(action)) {
                url = addToCart;
            } else if ("pay".equals(action)) {
                url = PayServlet;
            } else if("filter".equals(action)){
                url = filterServlet;
            } else if("viewreport".equals(action)){
                url = viewReportServlet;
            } else if("report".equals(action)){
                url = reportServlet;
            } else if("update".equals(action)){
                url = updateProduct;
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            System.out.println("url in main: " + url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
