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
public class AddToCartServlet extends HttpServlet {

    private final String viewHomePageUserServlet = "ViewHomePageUserServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = viewHomePageUserServlet;
        String id = request.getParameter("id");
        HashMap<Integer, CartItem> cart = null;
        ProductDAO pDao = new ProductDAO();
        CartItem item = null;
        CartDAO cartDao = new CartDAO();
        try {
            HttpSession sessionCart = request.getSession();
            cart = (HashMap<Integer, CartItem>) sessionCart.getAttribute("cart");
            // kiểm tra có giỏ hàng chưa
            if (cart == null) {
                Cookie cookies = cartDao.getCookieByName(request, "cart");
                if (cookies != null && !cookies.getValue().isEmpty()) {
                    cart = cartDao.getCartFromCookie(cookies);
                } else {
                    cart = new HashMap<>();
                }
            }
            ProductViewDTO productSelected = pDao.getByID(id);
            // kiểm tra trong cart có productselected ch
            item = cart.get(productSelected.getProductId());

            // thêm sản phẩm vào giỏ hàng
            if (item == null) {
                item = new CartItem(productSelected, 1);
                System.out.println("item: " + item.getProduct().toString());
                cart.put(item.getProduct().getProductId(), item);
            } else {
                item.setQuantity(item.getQuantity() + 1);
            }
            String strItemInCart = cartDao.convertCartToString(new ArrayList<>(cart.values()));

            cartDao.saveCartToCookie(request, response, strItemInCart);
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
