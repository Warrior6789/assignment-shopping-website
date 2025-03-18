<%-- 
    Document   : UpdateCategoryPage
    Created on : 03-Mar-2025, 14:35:16
    Author     : philo
--%>

<%@page import="dto.Categories"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>

            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }
            input[type=text] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }
            button[type=submit] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background-color: #04AA6D;
            }
        </style>
    </head>
    <body>
        <%
            Categories c = (Categories)request.getAttribute("cate");
            if(c != null){
        %>
        <form action="MainServlet" method="post" accept-charset="UTF-8">
            <div class="container">
                <h1>Update Category</h1>
                <p>Please fill in this form to Update Category</p>
                <hr>
                <input type="hidden" name="id" value="<%= c.getId() %>" />
                <label><b>Category Name</b></label>
                <input type="text" name="name" value="<%= c.getName() %>" required/>

                <label><b>Description</b></label>
                <input type="text" name="desc" value="<%= c.getDescription() %>" required/>

                <button type="submit" name="action" value="updatecate" >update</button>
            </div>
        </form>
        <%
            }
        %>
    </body>
</html>
