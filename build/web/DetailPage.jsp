<%-- 
    Document   : DetailPage
    Created on : 01-Mar-2025, 11:56:39
    Author     : philo
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dto.ProductViewDTO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DETAIL PAGE</title>
        <style>
            body{
                font-family: sans-serif;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
            .product-container {
                display: flex;
                width: 80%;
                max-width: 1000px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .product-image {
                flex-basis: 40%;
            }
            .product-image img {
                width: 100%;
                display: block;
            }
            .product-details {
                flex-basis: 50%;
                padding: 40px;
                text-align: center;
                background-color: #f9f9f9;
            }
            .product-title {
                font-size: 2em;
                font-weight: bold;
                margin: 20px 0;
            }
            .product-price {

                font-size: 2em;
                font-weight: bold;
                margin: 20px 0;
            }

            .product-category, .product-description { 
                color: #666;
                margin-bottom: 30px;
            }

        </style>
    </head>
    <body>
        <%
            NumberFormat currency = NumberFormat.getInstance(Locale.US);
            ProductViewDTO p = (ProductViewDTO) request.getAttribute("product");
            if (p != null) {
        %>
        <div class="product-container">
            <div class="product-image">
                <img src="<%= p.getProductImage()%>" alt="Mô tả ảnh" width="300" height="200">
            </div>

            <div class="product-detail">
                <h2 class="product-title" ><%= p.getProductName()%></h2>
                <p class="product-price">PRICE:<%= currency.format(p.getUnitPrice()) + " VND"%></p>
                <p class="product-category">CATEGORY:<%=   p.getCategoryName()%></p>
                <p class="product-description">DESCRIPTION:<%= p.getDescription()%></p>
            </div>

        </div>
        <%
            }
        %>
    </body>
</html>
