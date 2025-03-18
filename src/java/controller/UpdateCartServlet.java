/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dao.ProductDAO;
import dto.CartItem;
import dto.ProductViewDTO;
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
public class UpdateCartServlet extends HttpServlet {

    private final String viewCartPage = "ViewCartPageServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          request.setCharacterEncoding("UTF-8");
        System.out.println("======================= IN UPDATE CART SERVLET =======================");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id in update cart: " + id);
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));
        System.out.println("new quantity in update cart: " + newQuantity);
        HttpSession sessionCart = request.getSession();
        CartDAO cartDao = new CartDAO();
        String url = viewCartPage;
        try {
            HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) sessionCart.getAttribute("cart");
            if (cart == null) {
                Cookie cookie = cartDao.getCookieByName(request, "cart");
                if (cookie == null) {
                    System.out.println("empty");
                } else {
                    cart = cartDao.getCartFromCookie(cookie);
                }
            }
            CartItem item = cart.get(id);
           
            if (item != null) {
                item.setQuantity(newQuantity);
            }
             System.out.println(item.toString());
            sessionCart.setAttribute("cart", cart);
            String str = cartDao.convertCartToString(new ArrayList<>(cart.values()));
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
