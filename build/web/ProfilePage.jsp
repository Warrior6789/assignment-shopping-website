<%-- 
    Document   : AccountPage
    Created on : 03-Mar-2025, 19:03:52
    Author     : philo
--%>

<%@page import="dto.Account"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .info-content label,input{
                display: block;
                margin-bottom: 10px;

            }
            .info-content input{
                width: 100%;
                padding: 8px;
                background: #f1f1f1;
                border-radius: 8px;
            }
            .savebtn{
                width: 100%;
                padding: 8px;
                background-color: #04AA6D;
                color: white;
                 border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <%
            HttpSession sessionAcc = request.getSession();
            Account acc = (Account) sessionAcc.getAttribute("acc");
        %>
        <div class="profile">
            <form action="MainServlet" method="post">
                <div class="info-content">
                    <input type="hidden" name="id" value="<%= acc.getId() %>" />
                    <label>UserName</label>
                    <input type="text" name="username" value="<%= acc.getUserName()%>" />
                    <label>Password</label>
                    <input type="password" name="pass" value="<%= acc.getPassword()%>" />
                    <label>FullName</label>
                    <input type="text" name="fullname" value="<%= acc.getFullName()%>" />
                    <button class="savebtn" name="action" value="save">Save Change</button>
                </div>
            </form>
        </div>
    </body>
</html>
