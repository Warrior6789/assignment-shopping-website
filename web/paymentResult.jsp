<%-- 
    Document   : paymentResult
    Created on : 18-Mar-2025, 09:50:34
    Author     : philo
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thanh Toán Thành Công</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .payment-success-container {
                background-color: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                text-align: center;
                width: 100%;
                max-width: 500px;
            }

            .payment-success h1 {
                color: #28a745;
                font-size: 24px;
                margin-top: 20px;
            }

            .payment-success p {
                font-size: 16px;
                margin: 10px 0;
            }

            .check-icon img {
                width: 60px;
                height: 60px;
                margin-bottom: 20px;
            }

            .back-to-dashboard {
                display: inline-block;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-top: 20px;
            }

            .back-to-dashboard:hover {
                background-color: #0056b3;
            }

        </style>
    </head>
    <body>
        <%
            String mess = (String) request.getAttribute("message");
            String orderId = (String) request.getAttribute("orderId");
            String amount = (String) request.getAttribute("amount");

            if (mess == null) {
                mess = "Giao dịch thất bại!";
            }
            if (orderId == null) {
                orderId = "Không xác định";
            }
            if (amount == null) {
                amount = "0";
            }
        %>
        <div class="payment-success-container">
            <div class="payment-success">
                <div class="check-icon">
                    <img src="images/check-icon.png" alt="Success" style="width: 60px; height: 60px;">
                </div>
                <h1><%= mess%></h1>
                <p>Mã đơn: <span id="order-id"><%= orderId%></span> - Tổng tiền: <span id="total-amount"><%= amount%> VND</span></p>
                <a href="ViewHomePageUserServlet" class="back-to-dashboard">Quay về Menu</a>
            </div>
        </div>
    </body>
</html>
