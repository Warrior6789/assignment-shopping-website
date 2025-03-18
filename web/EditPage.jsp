<%-- 
    Document   : EditPage
    Created on : 01-Mar-2025, 15:48:42
    Author     : philo
--%>

<%@page import="dto.Supplier"%>
<%@page import="java.util.List"%>
<%@page import="dto.Categories"%>
<%@page import="dto.ProductViewDTO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                margin: 0;
            }
            h1{

                margin-left: 10px;
            }
            input[type=text],select {
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
            label{
                margin-left: 10px;
                margin-bottom: 10px;

            }

            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }
        </style>
    </head>
    <body>

        <%
            ProductViewDTO p = (ProductViewDTO) request.getAttribute("product");
            List<Categories> listCate = (List<Categories>) request.getAttribute("listcategory");
            List<Supplier> listSup = (List<Supplier>) request.getAttribute("listsupplier");
            if (p != null) {
        %>
        <div class="form-update">
            <h1>Update Form</h1>
            <p>Please fill in this form to Update Product</p>
            <hr>

            <form action="UpdateServlet" method="post" enctype="multipart/form-data"  accept-charset="UTF-8">

                <input type="hidden" name="id" value="<%= p.getProductId()%>" />

                <label>ProductName</label><br>
                <input type="text" name="productname" value="<%= p.getProductName()%>" required /><br>

                <label>ProductPrice</label><br>
                <input type="text" name="productprice" value="<%= p.getUnitPrice()%>" required /><br>


                <label>Quantity</label><br>
                <input type="text" name="quantity" value="<%= p.getQuantityPerUnit()%>" required/><br>

                <label>Supplier</label><br>
                <select name="supplierid" required>
                    <%
                        for (Supplier s : listSup) {
                            String selected = s.getCompanyName().equals(p.getSupplierName()) ? "selected" : "";

                    %>
                    <option value="<%= s.getId()%>" <%= selected%> ><%= s.getCompanyName()%></option>
                    <%
                        }
                    %>
                </select><br>

                <label>Category</label><br>
                <select name="categoryid" required>
                    <%
                        for (Categories c : listCate) {
                            String selected = (p.getCategoryName() != null && p.getCategoryName().equals(c.getName())) ? "selected" : "";

                    %>
                    <option value="<%= c.getId()%>" <%= selected%>><%= c.getName()%></option>
                    <%
                        }
                    %>
                </select><br>

                <label>Product Image</label><br>
                <img src="<%= p.getProductImage()%>" alt="Mô tả ảnh" width="100" height="150" ><br>
                <input type="file" name="photo" /><br>

                <button type="submit" name="action" value="Update" >update</button>
            </form>
        </div>



        <%
            }
        %>
    </body>
</html>
