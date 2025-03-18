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
import java.util.List;
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
public class ViewCartPageServlet extends HttpServlet {

    private final String viewCartPage = "CartPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = viewCartPage;
        HashMap<Integer, CartItem> cart = null;
        List<CartItem> itemInCart = null;
        CartDAO cartDao = new CartDAO();
        System.out.println("======================= IN VIEW CART SERVLET =======================");
        try {
            HttpSession sessionCart = request.getSession();
            cart = (HashMap<Integer, CartItem>) sessionCart.getAttribute("cart");
            System.out.println("check cart: ");

            if (cart == null) {
                System.out.println("cart session is null ");
                // kiểm tra trong cookie
                Cookie cookie = cartDao.getCookieByName(request, "cart");

                if (cookie != null && !cookie.getValue().isEmpty()) {
                    cart = cartDao.getCartFromCookie(cookie);
                    System.out.println("cart size in cookies: " + cart.size());

                } else {
                    System.out.println("cart cookie is null");
                    cart = new HashMap<>();
                    sessionCart.setAttribute("cart", cart);
                }
            }
            if (cart != null) {
                itemInCart = new ArrayList<>(cart.values());
            }
            if (itemInCart != null) {
                System.out.println("Number of items in cart: " + itemInCart.size());
            }
            request.setAttribute("cart", itemInCart);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        } finally {
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
