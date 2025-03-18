<%-- 
    Document   : HomepageUser
    Created on : 03-Mar-2025, 23:05:32
    Author     : philo
--%>

<%@page import="dto.Account"%>
<%@page import="dto.Categories"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="org.apache.tomcat.jni.Local"%>
<%@page import="java.util.List"%>
<%@page import="dto.ProductViewDTO"%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Reset CSS */
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            body {
                font-family: 'Roboto', sans-serif;
                margin: 0;
                min-height: 100vh;
                background-color: #f8f8f8;
                color: #333;
            }

            a {
                text-decoration: none;
                color: inherit;
            }

            header {
                background-color: #3498db;
                color: white;
                padding: 10px 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            header nav {
                display: flex;
                justify-content: space-between;
                width: 100%;
            }

            header nav .right-links {
                display: flex;
            }

            header nav a {
                margin: 0 15px;
                color: white;
                font-weight: 500;
                transition: color 0.3s;
            }

            header nav a:hover {
                color: #f0f0f0;
            }

            .content {
                width: 90%;
                max-width: 1200px;
                margin: 20px auto;
            }

            .product-grid {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                gap: 20px;
            }

            .product-card {
                background-color: white;
                padding: 20px;
                text-align: center;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s;
            }

            .product-card:hover {
                transform: translateY(-5px);
            }

            .product-card img {
                width: 100%;
                height: 200px; /* Điều chỉnh chiều cao ảnh */
                object-fit: contain; /* Hoặc cover tùy theo nhu cầu */
                max-width: 100%; /* Giới hạn chiều rộng tối đa */
                max-height: 200px; /* Giới hạn chiều cao tối đa */
                image-rendering: auto; /* Hoặc crisp-edges */
                margin-bottom: 10px;
                border-radius: 5px;
            }
            .price {
                font-weight: bold;
                color: #e74c3c;
            }

            .category {
                font-style: italic;
                color: #666;
            }

            .add-button {
                background-color: #2ecc71;
                color: white;
                padding: 10px 15px;
                border: none;
                cursor: pointer;
                width: 100%;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            .add-button:hover {
                background-color: #27ae60;
            }

            h1 {

                text-align: center;
                margin-top: 10px;
                margin-bottom: 20px;
            }

            .form-search {

                margin: 0 auto; /* Căn giữa form tìm kiếm */
                margin-bottom: 20px;
                text-align: left; /* Căn giữa các phần tử trong form */
            }

            input[type="text"] {
                width: 20%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                transition: border-color 0.3s;
            }

            input[type="text"]:focus {
                border-color: #3498db;
            }

            .searchbtn {
                padding: 10px 15px;
                background-color: #3498db;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .searchbtn:hover {
                background-color: #2980b9;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropbtn {
                background-color: transparent;
                color: white;
                padding: 10px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #f1f1f1;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #ddd;
                color: black;
            }
            .search-box {
                position: relative;
                display: inline-block;
            }

            .search-box input[type="text"] {
                padding: 10px 40px 10px 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #fff;
                font-size: 16px;
                width: 300px;
                outline: none;
                transition: border-color 0.3s;
            }

            .search-box input[type="text"]:focus {
                border-color: #3498db;
            }

            button[type="submit"] {
                position: absolute;
                top: 50%;
                right: 10px;
                transform: translateY(-50%);
                background: none;
                border: none;
                font-size: 18px;
                color: #999;
                cursor: pointer;
                outline: none;
            }
            .search-box{
                margin-left: 460px;
            }

        </style>
    </head>
    <body>
        <%
            NumberFormat currencyformat = NumberFormat.getInstance(Locale.US);
        %>
        <%
            List<ProductViewDTO> list = (List<ProductViewDTO>) request.getAttribute("list");
            HttpSession sessionAcc = request.getSession();
            Account acc = (Account)session.getAttribute("acc");
        %>
        <header>
            <nav>
                <div>
                    <a>PizzaStore</a>
                    <a href="ViewHomePageUserServlet">Pizzas</a>
                    <div class="dropdown">
                        <a href="#" class="dropbtn">Categories</a>
                        <div class="dropdown-content">
                            <%
                                List<Categories> listcate = (List<Categories>) request.getAttribute("listcate");

                                if (listcate != null) {

                                    for (Categories cate : listcate) {

                            %>
                            <a href="MainServlet?action=filter&Categoryid=<%= cate.getId()%>"><%= cate.getName()%></a>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>

                    <a href="ViewCartPageServlet">Reviews</a>
                </div>
                <div class="right-links"> 
                   
                    <%
                        if(acc != null){
                    %>
                    <a href="LogoutServlet">Logout</a>
                    <%
                        }else{
                    %>
                     <a href="RegisterPage.jsp">Register</a>
                    <a href="LoginPage.jsp">Login</a>
                    <%
                    }
                    %>
                </div>

            </nav>
        </header>


        <h1>MENU</h1> 

        <div class="container" >
            <form class="form-search" action="MainServlet" method="post">
                <div class="search-box">
                    <%
                        String value = (String) request.getAttribute("value");
                        if (value == null) {
                            value = "";
                        }
                    %>

                    <input type="text"  name="value" value="<%= value%>">
                    <button class="searchbtn" type="submit" name="action" value="usersearch">Search</button>
                </div>
            </form>
            <form action="MainServlet" method="post"> 
                <div class="content">
                    <div class="product-grid">
                        <% for (ProductViewDTO p : list) {%>
                        <div class="product-card">
                            <form action="MainServlet" method="post"> 
                                <input type="hidden" name="id" value="<%= p.getProductId()%>">
                                <img src="<%= p.getProductImage()%>" alt="img">
                                <h2><%= p.getProductName()%></h2>
                                <p class="price"><%= currencyformat.format(p.getUnitPrice())%> VND</p>
                                <p class="category"><%= p.getCategoryName()%></p>
                                <p class="description"><%= p.getDescription()%></p>
                                <button class="add-button" name="action" value="addtocart">Add Pizza</button>
                            </form>
                        </div>
                        <% }%>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
