<%-- 
    Document   : CreateCategoryPage
    Created on : 03-Mar-2025, 13:09:16
    Author     : philo
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                margin: 0;
                padding: 0;

            }
            .content{
                margin-left: 4px;
                margin-right: 4px;
                
            }
            .content label,input,textarea{
                display: block;
                width: 100%;
                 margin-bottom: 20px;
            }
            .content input{
                padding: 12px 20px;
                box-sizing: border-box;
               
                box-sizing: border-box;
                border: 2px solid;
                border-radius: 4px;
                
            }
            .submitbtn{
                background-color: #3CBC8D;
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
        <div class="content">
            <h1>Create Category</h1>
            <hr>
            <form action="MainServlet" method="post">
                <label>Name</label>
                <input type="text" name="name" placeholder="Enter Name" required />
                <label>Description</label>
                <textarea name="desc" style=" height: 200px" required> 
                </textarea>

                <input class="submitbtn" type="submit" name="action" value="createCate" />
            </form>
        </div>
    </body>
</html>
