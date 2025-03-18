/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author philo
 */
public class LoginServlet extends HttpServlet {
    private final String viewHomePage = "ViewHomePageServlet";
    private final String viewUserHomePage = "ViewHomePageUserServlet";
    private final String loginPage = "LoginPage.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        System.out.println("name: "+name+" pass: "+pass);
        String url = null;
        try {
            AccountDAO accountDao = new AccountDAO();
            Account acc = accountDao.login(name, pass);
            HttpSession sessionAcc = request.getSession();
            sessionAcc.setAttribute("acc", acc);
            if (acc != null) {
                if(acc.getType() == 1){
                     url = viewHomePage;
                }else{
                    url = viewUserHomePage;
                }
               
            }else{
                url = loginPage;
                String mess = "Username or Password wrong!";
                request.setAttribute("mess", mess);
            }
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }finally{
           
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
