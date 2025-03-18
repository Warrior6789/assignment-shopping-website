<%-- 
    Document   : LoginPage
    Created on : 02-Mar-2025, 18:02:57
    Author     : philo
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body {
                font-family: sans-serif;
                background: linear-gradient(135deg, #e0e0e0, #f0f0f0);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            header {
                position: fixed; /* Fixed header */
                top: 0;
                left: 0;
                width: 100%;
                background-color: #3498db;
                color: white;
                padding: 10px 0;
                z-index: 1000; /* Ensure it's on top */
            }

            nav {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 80%; /* Limit width */
                margin: 0 auto; /* Center the nav */
            }

            .left-links a {
                color: white;
                text-decoration: none;
                margin-right: 20px;
                font-weight: bold; /* Bold text */
            }

            .right-links a {
                color: white;
                text-decoration: none;
                margin-left: 20px;
            }


            .login-container {
                background-color: rgba(255, 255, 255, 0.8); /* White background with transparency */
                border-radius: 15px;
                padding: 30px;
                width: 350px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .login-box h2 {
                text-align: center;
                margin-bottom: 25px;
                color: #333;
            }

            .input-group {
                margin-bottom: 20px;
            }

            .input-group label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }

            .input-group input {
                width: calc(100% - 22px);
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
            }

            button {
                width: 100%;
                padding: 12px;
                background-color: #3498db;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                cursor: pointer;
            }

            button:hover {
                background-color: #e0e0e0;
            }
            .error-message {
                background-color: #f8d7da; /* Light red background */
                color: #721c24; /* Dark red text */
                border: 1px solid #f5c6cb; /* Red border */
                border-radius: 5px;
                padding: 10px;
                margin-bottom: 15px; /* Add space below */
                text-align: center; /* Center the text */
            }

            .error-message p {
                margin: 0; /* Remove default paragraph margin */
            }
        </style>
    </head>
    <body>

        <header>
            <nav>
                <div class="left-links" > 
                    <a>PizzaStore</a>
                    <a href="ViewHomePageUserServlet">Pizzas</a>
                    <a href="ViewCartPageServlet">Reviews</a>
                </div>

                <div class="right-links"> 
                    <a href="RegisterPage.jsp">Register</a>
                    <a href="LoginPage.jsp">Log in</a>
                </div>
            </nav>
        </header>

        <div class="login-container">
            <div class="login-box">
                <h2>Login</h2>
                <%
                    String mess = (String) request.getAttribute("mess");
                    if (mess != null) {
                %>
                <div class="error-message">
                    <p><%= mess%></p>
                </div>
                <%
                    }
                %>
                <form action="MainServlet" method="post">
                    <div class="input-group">
                        <label for="username">Username</label>
                        <input type="text" name="name" placeholder="your username here">
                    </div>
                    <div class="input-group">
                        <label for="password">Password</label>
                        <input type="password"  name="pass" placeholder="your password here">
                    </div>
                    <button type="submit" name="action" value="login">Submit</button>
                </form>
            </div>
        </div>

    </body>
</html>
