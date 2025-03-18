package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class LoginPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                font-family: sans-serif;\n");
      out.write("                margin: 0;\n");
      out.write("                padding: 0;\n");
      out.write("                background-color:  #282c34;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            header {\n");
      out.write("                background-color: #a8c8fa; /* Màu xanh dương nhạt */\n");
      out.write("                color: black;\n");
      out.write("                padding: 10px;\n");
      out.write("                display: flex;\n");
      out.write("                justify-content: space-between;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            header nav {\n");
      out.write("                display: flex;\n");
      out.write("                justify-content: space-between;\n");
      out.write("                width: 100%; /* Đảm bảo nav chiếm toàn bộ chiều rộng của header */\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            header nav .right-links {\n");
      out.write("                display: flex; /* Để căn chỉnh các liên kết bên phải theo hàng ngang */\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            header nav a {\n");
      out.write("                color: black;\n");
      out.write("                text-decoration: none;\n");
      out.write("                margin-left: 10px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .login-container {\n");
      out.write("                background-color: #282c34; /* Màu xanh đen */\n");
      out.write("                color: white;\n");
      out.write("                width: 100%;\n");
      out.write("\n");
      out.write("                padding: 20px;\n");
      out.write("            }\n");
      out.write("            .form-username{\n");
      out.write("                margin-bottom: 10px;\n");
      out.write("            }\n");
      out.write("            .form-password{\n");
      out.write("                margin-left: 5px;\n");
      out.write("            }\n");
      out.write("            button{\n");
      out.write("                margin-left: 80px;\n");
      out.write("                margin-top: 10px;\n");
      out.write("                padding: 5px 20px;\n");
      out.write("                background-color: #a8c8fa;\n");
      out.write("                border-radius: 5px;\n");
      out.write("            }\n");
      out.write("            form{\n");
      out.write("                margin-left:  50px;\n");
      out.write("                margin-top: 30px;\n");
      out.write("            }\n");
      out.write("            h3{\n");
      out.write("                color: red;\n");
      out.write("                margin-left: 50px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <header>\n");
      out.write("            <nav>\n");
      out.write("                <div > \n");
      out.write("                    <a>PizzaStore</a>\n");
      out.write("                    <a>Pizzas</a>\n");
      out.write("                    <a>Categories</a>\n");
      out.write("                    <a>Reviews</a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"right-links\"> <a href=\"RegisterPage.jsp\">Register</a>\n");
      out.write("                    <a href=\"LoginPage.jsp\">Log in</a>\n");
      out.write("                </div>\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("        <div class=\"login-container\">\n");
      out.write("            <h1>Please log in here</h1>\n");
      out.write("            <p>Enter your details below</p>\n");
      out.write("            <form action=\"MainServlet\" method=\"POST\">\n");
      out.write("                <div class=\"form-username\">\n");
      out.write("                    <label for=\"username\">UserName</label>\n");
      out.write("                    <input type=\"text\" name=\"name\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-password\">\n");
      out.write("                    <label for=\"password\">Password</label>\n");
      out.write("                    <input type=\"password\" name=\"pass\"/>\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" name=\"action\" value=\"login\" class=\"loginbtn\">Login</button>\n");
      out.write("            </form>  \n");
      out.write("        </div>\n");
      out.write("        ");

                String mess = (String)request.getAttribute("mess");
                if(mess != null){
        
      out.write("\n");
      out.write("        <h3>");
      out.print( mess );
      out.write("</h3>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
