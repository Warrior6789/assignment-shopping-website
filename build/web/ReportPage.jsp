<%--
    Document     : ReportPage
    Created on : 07-Mar-2025, 15:51:38
    Author       : philo
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="dto.OrderView"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }

            .dashboard-cards {
                display: flex;
                justify-content: space-around;
                margin-bottom: 30px;
            }

            .dashboard-cards .card {
                background-color: #fff;
                border: 1px solid #ddd;
                padding: 30px;
                text-align: center;
                width: 45%;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .dashboard-cards .card-title {
                font-size: 2em;
                margin-bottom: 15px;
                color: #007bff;
            }

            .dashboard-cards .card-text {
                font-size: 1.2em;
                color: #555;
            }

            .content-report {
                margin-top: 30px;
            }

            .search-content {
                margin-bottom: 30px;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .search-content label {
                margin-right: 10px;
            }

            .reportbtn,
            .search-content input[type="date"] {
                border-radius: 8px;
                padding: 10px 15px;
                border: 1px solid #ddd;
                margin: 0 5px;
            }

            .reportbtn {
                background-color: #007bff;
                color: white;
                border: none;
                cursor: pointer;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            th,
            td {
                border-bottom: 1px solid #ddd;
                padding: 12px 15px;
                text-align: left;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            tr:hover {
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>
        <%
            NumberFormat currency = NumberFormat.getInstance(Locale.US);
            List<OrderView> list = (List<OrderView>) request.getAttribute("orderlist");
            System.out.println(list.size());
            if (list == null) {
        %>
        <p>EMPTY</p>
        <%
            }
            
            Integer totalOrder = (Integer)request.getAttribute("totalorder"); 
            Double totalRevenu = (Double)request.getAttribute("totalrevenu"); 
        %>
        <div>
            <h1 style="text-align: center ">DASHBOARD</h1>
            <div class="dashboard-cards">
                <div class="card revenu">
                    <h2 class="card-title"><%= currency.format(totalRevenu)  %> VND</h2>
                    <p class="card-text">Tổng doanh thu</p>
                </div>
                <div class="card order">
                    <h2 class="card-title"><%= totalOrder %></h2>
                    <p class="card-text">Tổng số đơn hàng</p>
                </div>
            </div>
        </div>

        <div class="content-report">
            <div class="search-content">
                <form action="MainServlet" method="post">
                    <label>StartDate</label>
                    <input type="date" name="startDate" required/>
                    <label>EndDate</label>
                    <input type="date" name="endDate" required/>
                    <input class="reportbtn" type="submit" name="action" value="report" />
                </form>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>ContactName</th>
                        <th>Address</th>
                        <th>TotalProduct</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        if(list != null){
                            for (OrderView o : list) {
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= o.getOrderdate()%></td>
                        <td><%= o.getContactname()%></td>
                        <td><%= o.getAddress()%></td>
                        <td><%= o.getTotalproduct()%></td>
                        <td><%= currency.format(o.getTotal())%> VND</td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>