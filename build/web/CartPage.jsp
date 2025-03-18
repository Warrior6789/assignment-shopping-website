<%--
    Document   : CartPage
    Created on : 04-Mar-2025, 15:12:28
    Author     : philo
--%>

<%@page import="dto.CustomerErr"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="dto.CartItem"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>

        <style>
            body {
                font-family: sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
                color: #333;
            }
            .container {
                display: flex;
                width: 80%;
                margin: 20px auto;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                overflow: hidden;
            }
            .cart-container {
                flex: 1;
                padding: 20px;
            }
            .checkout-container {
                flex: 1;
                background-color: #5850EC; /* Màu tím từ ảnh */
                color: white;
                padding: 20px;
            }
            .cart-header {
                border-bottom: 1px solid #eee;
                margin-bottom: 20px;
            }
            .cart-item {
                display: flex;
                align-items: center;
                padding: 15px; /* Tăng padding */
                border: 1px solid #eee; /* Thêm viền */
                margin-bottom: 10px; /* Thêm khoảng cách */
                background-color: white; /* Thêm nền trắng */
                border-radius: 5px; /* Thêm bo tròn góc */
                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1); /* Thêm đổ bóng nhẹ */
            }
            .cart-item img {
                width: 100px; /* Ảnh lớn hơn */
                height: 100px;
                object-fit: cover;
                margin-right: 20px;
                border-radius: 5px;
            }
            .item-details {
                flex-grow: 1;
                display: flex; /* Sử dụng Flexbox cho item-details */
                flex-direction: column; /* Sắp xếp theo cột */
                margin-left: 25px;
            }

            .item-details h3 {
                margin-bottom: 5px; /* Thêm khoảng cách dưới tên sản phẩm */
            }

            .item-details div {
                display: flex;
                align-items: center;
                margin-bottom: 5px; /* Thêm khoảng cách dưới input số lượng */
            }

            .item-details p {
                margin-bottom: 5px; /* Thêm khoảng cách dưới giá tiền */
            }

            input[type="number"] {
                width: 50px;
                margin-top: 10px; /* Thêm khoảng cách bên trái input số lượng */
            }
            .remove-btn {
                background: none;
                border: none;
                color: red;
                font-size: 16px;
                cursor: pointer;
                transition: color 0.3s ease;
            }
            .remove-btn:hover {
                color: darkred;
            }
            .checkout-title {
                text-align: center;
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: block;
                margin-bottom: 5px;
            }
            input[type="text"] {
                width: calc(100% - 22px);
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .checkoutbtn {
                background-color: #7bc37f;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
            }
            .total-info {
                margin-top: 20px;
                text-align: right;
            }
            .form-group p {
                color: red;
            }


        </style>
        <script>
            function updateQuantity(productId) {
                var quantity = document.getElementById('quantity_' + productId);
                var newQuantity = parseInt(quantity.value);
                console.log("productId: " + productId + ", newQuantity: " + newQuantity);

                fetch("UpdateCartServlet?id=" + productId + "&quantity=" + newQuantity)
                        .then(response => {
                            if (response.ok) {
                                location.reload();
                            } else {
                                console.error("error update cart");
                            }
                        });
            }
        </script>
    </head>
    <body>
        <%
            NumberFormat currencyFormatter = NumberFormat.getInstance(Locale.US);
            CustomerErr customerErr = (CustomerErr)request.getAttribute("error");
        %>
        <%
            double total = 0;
            double freight = 20000;
            int totalQuantity = 0;
            List<CartItem> list = (List<CartItem>) request.getAttribute("cart");
            if (list != null) {
        %>
        <div class="container">
            <div class="cart-container">
                <div class="cart-header">
                    <h2>Shopping Cart</h2>
                </div>
                <%
                    for (CartItem c : list) {
                        total += c.getTotal();
                        totalQuantity += c.getQuantity();
                %>
                <div class="cart-item">
                    <img src="<%= c.getProduct().getProductImage()%>" alt="Product Image">
                    <div class="item-details">
                        <h3><%= c.getProduct().getProductName()%></h3>
                        <div style="display: flex; align-items: center;">
                            <input type="number" id="quantity_<%= c.getProduct().getProductId()%>" name="quantity" value="<%= c.getQuantity()%>" onchange="updateQuantity('<%= c.getProduct().getProductId()%>')"/>                        </div>
                        <p>Price: <%= currencyFormatter.format(c.getProduct().getUnitPrice())%> VND</p>
                    </div>
                    <form action="DeleteItemServlet" method="post">
                        <input type="hidden" name="id" value="<%= c.getProduct().getProductId()%>">
                        <button type="submit" class="remove-btn">✖</button>
                    </form>
                </div>
                <%
                    }
                %>
                <a style="text-decoration: none; color: black" href="ViewHomePageUserServlet" class="back-btn">
                    ⬅ Back To Menu
                </a>
                <div class="total-info">
                    <h2>Total: <%= currencyFormatter.format(total)%> VND</h2>
                </div>
            </div>
            <div class="checkout-container">
                <div class="checkout-title">
                    <h2>Payment information</h2>
                </div>
                <form action="MainServlet" method="post">
                    <div class="form-group">
                        <label>ContactName:</label>
                        <input type="text" name="contactname" required/>
                        <p><%= (customerErr != null && customerErr.getContactName() != null) ? customerErr.getContactName() : ""%></p>
                    </div>

                    <div class="form-group">
                        <label>Address:</label>
                        <input type="text" name="address" required/>
                        <p><%= (customerErr != null && customerErr.getAddress() != null) ? customerErr.getAddress() : ""%></p>
                    </div>

                    <div class="form-group">
                        <label>Phone:</label>
                        <input type="text" name="phone" required pattern="^(\+84|0)[0-9]{9,10}$" title="Số điện thoại phải có 10 chữ số hoặc bắt đầu bằng +84"/>
                        <p><%= (customerErr != null && customerErr.getPhone()!= null) ? customerErr.getPhone(): ""%></p>
                    </div>

                    <input type="hidden" name="orderdate" value="<%= new java.util.Date().toString()%>" />
                    <div class="form-group">
                        <label>Freight:</label>
                        <input type="text" value="<%= currencyFormatter.format(freight)%>" readonly />
                        <input type="hidden" name="freight" value="<%= freight%>" />
                    </div>
                    <%
                        double totalPrice = total + freight;
                    %>
                    <p>Total Quantity: <%= totalQuantity%></p>
                    <input type="hidden" name="total" value="<%= totalPrice  %>" />
                    <p>Total Price: <%=  currencyFormatter.format(totalPrice)%> VND</p>
                    <button class="checkoutbtn" type="submit" name="action" value="pay">Pay</button>
                </form>
            </div>
        </div>
        <%
        } else {
        %>
        <h1 style="text-align: center">Cart is empty</h1>
        <%
            }
        %>
    </body>
</html>