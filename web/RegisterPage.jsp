<%-- 
    Document   : RegisterPage
    Created on : 02-Mar-2025, 17:36:38
    Author     : philo
--%>

<%@page import="dto.AccountErr"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            * {box-sizing: border-box}
            .container {
                padding: 16px;
            }
            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }
            input[type=text], input[type=password] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }
            .signin {
                background-color: #f1f1f1;
                text-align: center;
            }
            .registerbtn {
                background-color: #04AA6D;
                color: white;
                padding: 16px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
            }
        </style>
    </head>
    <body>
        <%
            String mess = (String) request.getAttribute("mess");
            AccountErr accountErr = (AccountErr) request.getAttribute("error");
        %>
        <form action="MainServlet" method="post">
            <div class="container">
                <h1>Register</h1>
                <p>Please fill in this form to create account</p>
                <hr>

                <label><b>UserName</b></label>
                <input type="text" placeholder="Enter UserName" name="username" required/>
                <% if (accountErr != null && accountErr.getUserName() != null) {%>
                <p style="color: red"><%= accountErr.getUserName()%></p>
                <%}%>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="pass" required/>
                <% if (accountErr != null && accountErr.getPassword()!= null) {%>
                <p style="color: red"><%= accountErr.getPassword()%></p>
                <%}%>
                
                <label><b>FullName</b></label>
                <input type="text" placeholder="Enter FullName" name="fullname" required/>
                <% if(accountErr != null && accountErr.getFullName()!= null){ %>
                <p style="color: red"><%= accountErr.getFullName()%></p>
                <%}%> 
                
                <button type="submit" name="action" value="register" class="registerbtn">Register</button>
            </div>
            <div class="signin ">
                <p>Already have an account? <a href="LoginPage.jsp">Sign in</a>.</p>
            </div>
        </form>
    </body>
</html>
