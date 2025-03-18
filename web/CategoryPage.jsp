<%-- 
    Document   : CategoryPage
    Created on : 03-Mar-2025, 12:26:32
    Author     : philo
--%>

<%@page import="dto.Categories"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                font-family: 'Roboto', sans-serif;
                margin: 0;
                min-height: 100vh;
                background-color: #f8f8f8;
                color: #333;
            }
            .content{
                padding: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }

            table, th, td {
                border-bottom: 1px solid #ddd;
                padding: 8px;
                text-align: left;

            }

            a{
                text-decoration: none;
            }
            button[type="submit"]{


                padding:8px;
                text-align: center;
                border-radius: 5px;
            }
            .form-create{
                margin-bottom: 10px;
            }
            td a{
                text-decoration: none;
                color: black;
            }
            h1{
                text-align: center;
            }
            .create-btn {
                background-color: #2ecc71; /* Màu xanh lá cây */
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                font-size: 1em;
                cursor: pointer;
                transition: background-color 0.3s;
            }
        </style>
    </head>
    <body>
        <%
            List<Categories> list = (List<Categories>) request.getAttribute("list");
        %>
        <div class="content">
            <h1>Categories</h1>
            <form class="form-create" action="CreateCategoryPage.jsp">
                <button class="create-btn" type="submit">Create</button>
            </form>
            <table>
                <thead>
                    <tr>
                        <th>CategoryID</th>
                        <th>CategoryName</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (Categories c : list) {
                    %>
                    <tr>
                        <td><%=++count%></td>
                        <td><%= c.getName()%></td>
                        <td><%= c.getDescription()%></td>
                        <td><a href="MainServlet?action=viewupdatecate&id=<%= c.getId()%>">Update|</a>
                            <a href="MainServlet?action=deletecate&id=<%= c.getId()%>">Delete</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
