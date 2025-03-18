/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dto.Account;
import dto.AccountErr;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    private final String loginPage = "LoginPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        String fullname = request.getParameter("fullname");
        int type = 2;
        Account acc = new Account(username, password, fullname, type);
        AccountDAO accDao = new AccountDAO();
        String mess = null;
        String url = loginPage;
        boolean isError = false;
        AccountErr accountErr = new AccountErr();
        if (username == null || username.trim().isEmpty()) {
            accountErr.setUserName("Username cannot empty.");
            isError = true;
        }
        if (password == null || password.trim().isEmpty()) {
            accountErr.setPassword("Password cannot empty.");
            isError = true;
        }
        if (fullname == null || fullname.trim().isEmpty()) {
            accountErr.setFullName("Fullname cannot empty.");
            isError = true;
        }

        try {

            if (isError == false) {
                Account existAccount = accDao.getByName(acc.getUserName());

                if (existAccount == null) {
                    if (accDao.register(acc)) {
                        mess = "Successfully Registed";
                    } else {
                        mess = "Fail Registed";
                    }
                } else {
                    mess = "account is existed";
                }
            }
            request.setAttribute("error", accountErr);
            request.setAttribute("mess", mess);
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        } finally {
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
