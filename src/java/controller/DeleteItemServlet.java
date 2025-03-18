/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dto.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author philo
 */
public class DeleteItemServlet extends HttpServlet {

    private final String viewCartPage = "ViewCartPageServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("======================= IN DELETE CART SERVLET =======================");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id in delete cart: " + id);
        HashMap<Integer, CartItem> cart = null;
        CartDAO cartDao = new CartDAO();
        String mess;
        String url = viewCartPage;
        try {
            HttpSession sessionCart = request.getSession();
            cart = (HashMap<Integer, CartItem>) sessionCart.getAttribute("cart");
            if (cart == null) {
                Cookie cookies = cartDao.getCookieByName(request, "cart");
                if (cookies == null) {
                    System.out.println("empty");
                }
                cart = cartDao.getCartFromCookie(cookies);
            }
            System.out.println("cart size before delete: " + cart.size());
            CartItem removeCart = cart.remove(id);
            if (removeCart != null) {

                mess = "remove success";
                System.out.println(mess);
            } else {
                mess = "remove fail";
                System.out.println(mess);
            }
            System.out.println("cart size after delete: " + cart.size());
            sessionCart.setAttribute("cart", cart);
            String str = cartDao.convertCartToString(new ArrayList<>(cart.values()));
            System.out.println("str: " + str);
            cartDao.saveCartToCookie(request, response, str);
        } catch (Exception e) {
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
