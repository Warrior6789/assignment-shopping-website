<%-- 
    Document   : CreatePage
    Created on : 28-Feb-2025, 16:19:14
    Author     : philo
--%>

<%@page import="dto.ProductErr"%>
<%@page import="dto.Categories"%>
<%@page import="dto.Supplier"%>
<%@page import="java.util.List"%>
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
                margin-left: 5px;
                margin-right: 5px;
                margin-top: 10px;
            }
            .content label,input,select{
                display: block;
                margin-bottom: 10px;
                padding: 8px;
                width: 100%;
                border-radius: 5px;

            }
            .content input[type=text], input[type=select]{
                background: #f1f1f1;
            }
            .createbtn{
                width: 100%;
                color: white;
                background-color: #04AA6D;
                padding: 8px;
            }
            input[type=file]{

                border-radius: 5px;
            }
            p{
                color: red;
            }
        </style>
    </head>
    <body>
        <%
            List<Supplier> listSup = (List<Supplier>) request.getAttribute("supList");
            List<Categories> listCate = (List<Categories>) request.getAttribute("cateList");
            ProductErr productErr = (ProductErr) request.getAttribute("error");

        %>

        <div class="content">
            <h1>Create Product</h1>
            <hr>
            <form action="CreateServlet" method="post" enctype="multipart/form-data" >

                <label>ProductName</label>
                <input type="text" name="productname" placeholder="Enter Product Name" required/>
                <p><%= (productErr != null && productErr.getProductName()!= null) ? productErr.getProductName(): ""%></p>

                <label>ProductPrice</label>
                <input type="text" name="price" placeholder="Enter Product Price" required/>
                <p><%= (productErr != null && productErr.getUnitPrice() != null) ? productErr.getUnitPrice() : ""%></p>

                <label>Supplier</label>
                <select name="supplierid" required>
                    <%                        for (Supplier s : listSup) {
                    %>
                    <option value="<%= s.getId()%>"> <%= s.getCompanyName()%> </option>
                    <%
                        }
                    %>
                </select>

                <label>Category</label>
                <select name="categoryid" required>
                    <%
                        for (Categories c : listCate) {
                    %>
                    <option value="<%= c.getId()%>"> <%= c.getName()%> </option>
                    <%
                        }
                    %>
                </select>

                <label>Quantity</label>
                <input type="text" name="quantity" placeholder="Enter Quantity" required/>
                <p><%= (productErr != null && productErr.getQuantityPerUnit() != null) ? productErr.getQuantityPerUnit() : ""%></p>
                <label>Image</label>
                <img id="previewImage" style="max-width: 100px">
                <input type="file" id="imageInput" accept="image/*" name="photo"  /></br>

                <script>
                    const imageInput = document.getElementById("imageInput");
                    const previewImage = document.getElementById("previewImage");

                    imageInput.addEventListener("change", function () {
                        const file = this.files[0];
                        if (file) {
                            const reader = new FileReader();
                            reader.onload = function (event) {
                                previewImage.src = event.target.result;
                            };
                            reader.readAsDataURL(file);
                        }
                    });
                </script>
                <input type="submit" name="action" value="create" class="createbtn" />

            </form>

        </div>
    </body>
</html>
