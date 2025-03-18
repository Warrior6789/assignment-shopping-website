/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CartItem;
import dto.ProductViewDTO;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author philo
 */
public class CartDAO {

    public Cookie getCookieByName(HttpServletRequest request, String name) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals(name)) {
                        return c;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    public HashMap<Integer, CartItem> getCartFromCookie(Cookie cookie) {
        HashMap<Integer, CartItem> cart = new HashMap<>();

        try {

            if (cookie != null) {
                String cookieValue = cookie.getValue();
                if (cookieValue == null || cookieValue.isEmpty()) {
                    System.out.println("cookie is null");
                    return cart;
                }
                byte[] decode = Base64.getDecoder().decode(cookieValue);
                String decodeCookieValue = new String(decode, StandardCharsets.UTF_8);

                System.out.println("decodeValue in cookie: " + decodeCookieValue);
                String[] cartData = decodeCookieValue.split(",");
                for (String itemData : cartData) {
                    CartItem item = convertStringToCartItem(itemData);
                    cart.put(item.getProduct().getProductId(), item);
                }
            }
            return cart;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    public CartItem convertStringToCartItem(String data) {
        CartItem item = null;
        try {
            String[] parts = data.split(":");
            int id = Integer.parseInt(parts[0]);
            String img = parts[1];
            String name = parts[2];
            int quantity = Integer.parseInt(parts[3]);
            double price = Double.parseDouble(parts[4]);
            ProductViewDTO p = new ProductViewDTO(id, name, price, img);
            item = new CartItem(p, quantity);
        } catch (NumberFormatException e) {
            System.out.println("error: " + e.getMessage());
        }
        return item;
    }

    public void saveCartToCookie(HttpServletRequest request, HttpServletResponse response, String strItemInCart) {
        String CookieName = "cart";
        Cookie cookie = getCookieByName(request, CookieName);
        if (cookie != null) {
            cookie.setValue(strItemInCart);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        } else {
            cookie = new Cookie(CookieName, strItemInCart);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        }
    }

    public String convertCartToString(ArrayList<CartItem> item) {

        StringBuilder sb = new StringBuilder();
        try {
            for (CartItem cartItem : item) {
                System.out.println("name: " + cartItem.getProduct().getProductName());
                sb.append(cartItem.getProduct().getProductId()).append(":").append(cartItem.getProduct().getProductImage()).append(":")
                        .append(cartItem.getProduct().getProductName()).append(":").append(cartItem.getQuantity()).append(":").append(cartItem.getProduct().getUnitPrice())
                        .append(",");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            System.out.println("data before: " + sb.toString());
            String stringData = sb.toString();
            String StringDataAfterEncode = Base64.getEncoder().encodeToString(stringData.getBytes(StandardCharsets.UTF_8));
            System.out.println("data after: " + StringDataAfterEncode);
            return StringDataAfterEncode;
        } catch (Exception e) {
            System.err.println("Error converting cart to string: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
