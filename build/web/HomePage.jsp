<%-- 
    Document   : HomePage
    Created on : 28-Feb-2025, 00:05:08
    Author     : philo
--%>

<%@page import="dto.Categories"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="dto.Account"%>
<%@page import="dto.ProductViewDTO"%>
<%@page import="dto.Products"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza Store</title>
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

            /* Header */
            header {
                background-color: #3498db;
                padding: 10px 20px;
                border-bottom: 2px solid #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            nav {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            nav a {
                text-decoration: none;
                margin: 0 15px;
                color: #fff;
                font-weight: 500;
                transition: color 0.3s;
            }

            nav a:hover {
                color: #f0f0f0;
            }

            /* Form tìm kiếm */
            .search_create_content {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
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

            .search-box button[type="submit"] {
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

            /* Nút Create New */
            .search_create_content form:last-child button[type="submit"] {
                background-color: #2ecc71;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                font-size: 1em;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .search_create_content form:last-child button[type="submit"]:hover {
                background-color: #27ae60;
            }

            /* Content */
            .content {
                padding: 20px;
                margin: 0 auto;
                width: 100%;
            }

            h1 {
                margin-bottom: 20px;
                text-align: center;
              
            }

            /* Table (giữ nguyên) */
            table {
                width: 100%;
                border-collapse: collapse;
            }

            table, th, td {
                border-bottom: 1px solid #ddd;
                padding: 8px;
                text-align: left;

            }

            th {
                background-color: #e0e0e0;
            }

            td a {
                text-decoration: none;
                color: black;
            }

            /* Thông báo lỗi */
            .error-message {
                color: red;
                text-align: center;
                margin-bottom: 10px;
            }

            /* Footer (tùy chọn) */
            footer {
                text-align: center;
                padding: 20px;
                background-color: #333;
                color: #fff;
                margin-top: 20px;
            }
        </style>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>
        <%
            NumberFormat currency = NumberFormat.getInstance(Locale.US);
            List<ProductViewDTO> list = (List<ProductViewDTO>) request.getAttribute("list");
            List<Categories> listcate = (List<Categories>) request.getAttribute("listcate");
            HttpSession sessionAcc = request.getSession();
            Account acc = (Account) sessionAcc.getAttribute("acc");
            String type = "User";
        %>
        <%
            if (list != null) {
        %>
        <header>
            <nav>
                <div>
                    <a>PizzaStore</a>
                    <a href="ViewHomePageUserServlet">Pizzas</a>
                    <a href="MainServlet?action=viewcate">Categories</a>
                    <a href="MainServlet?action=viewreport">Reviews</a>
                   
                </div>

                <div>
                    <%
                        if (acc.getType() == 1) {
                            type = "Admin";
                        }
                    %>
                    <a><%=type%> Page</a>
                    <span>Welcome <%=type%></span>
                    <a href="LogoutServlet">Log Out</a>  
                </div>
            </nav>
        </header>
        <div class="content">
            <h1>Pizzas</h1>
            <div class="search_create_content">
                <form  action="MainServlet" method="post" accept-charset="UTF-8">
                    <div class="search-box">
                        <%
                            String value = (String) request.getAttribute("value");
                            if (value == null) {
                                value = "";
                            }
                        %>

                        <input type="text"  name="value" value="<%= value%>">
                        <button type="submit" name="action" value="search">Search</button>
                        
                    </div>

                </form>

                <form action="MainServlet" method="post">
                    <button type="submit" name="action" value="viewcreatepage">Create New</button>
                </form>
            </div>

            <%
                String mess = (String) request.getAttribute("mess");
                if (mess != null) {
            %>
            <p style="color: red "><%= mess%></p>
            <%
                }
            %>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>ImageUrl</th>
                        <th>IsPizzaOfTheWeek</th>
                        <th>
                            Category
                        </th>
                        <th></th>
                    </tr>
                </thead>
                <%
                    for (ProductViewDTO p : list) {
                %>
                <tbody>
                    <tr>
                        <td><%= p.getProductName()%></td>
                        <td><%= currency.format(p.getUnitPrice())%> VND</td>
                        <td><%= p.getDescription()%></td>
                        <td><%= p.getProductImage()%></td>
                        <td><input type="checkbox" name="ispizzaoftheweek"/></td>
                        <td><%= p.getCategoryName()%></td>
                        <td>
                            <a href="MainServlet?action=edit&id=<%= p.getProductId()%>">Edit|</a>
                            <a href="MainServlet?action=detail&id=<%= p.getProductId()%>">Details|</a>
                            <a href="MainServlet?action=delete&id=<%= p.getProductId()%>">Delete</a>
                        </td>
                    </tr>
                </tbody>
                <%
                    }
                %>
            </table> 
        </div>

        <%
            }
        %>
    </body>
</html>
