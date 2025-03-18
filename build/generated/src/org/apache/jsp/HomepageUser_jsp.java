package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class HomepageUser_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("            .content {\n");
      out.write("                width: 90%;\n");
      out.write("                margin: 20px auto;\n");
      out.write("            }\n");
      out.write("            .product-grid {\n");
      out.write("                display: flex;\n");
      out.write("                justify-content: space-around;\n");
      out.write("                flex-wrap: wrap;\n");
      out.write("            }\n");
      out.write("            .product-card {\n");
      out.write("                width: 300px;\n");
      out.write("                background-color: #333;\n");
      out.write("                padding: 15px;\n");
      out.write("                margin-bottom: 20px;\n");
      out.write("                text-align: center;\n");
      out.write("                border: 1px solid #ddd;\n");
      out.write("                border-radius: 3px;\n");
      out.write("            }\n");
      out.write("            .product-card img {\n");
      out.write("                width: 100%;\n");
      out.write("                height: auto;\n");
      out.write("                margin-bottom: 10px;\n");
      out.write("            }\n");
      out.write("            .price {\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .category {\n");
      out.write("                font-style: italic;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .add-button {\n");
      out.write("                background-color: #4CAF50;\n");
      out.write("                color: white;\n");
      out.write("                padding: 10px 15px;\n");
      out.write("                border: none;\n");
      out.write("                cursor: pointer;\n");
      out.write("                width: 100%;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .add-button:hover {\n");
      out.write("                background-color: #45a049;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <nav>\n");
      out.write("                <div>\n");
      out.write("                    <a>PizzaStore</a>\n");
      out.write("                    <a>Pizzas</a>\n");
      out.write("                    <a>Categories</a>\n");
      out.write("                    <a>Reviews</a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"right-links\"> \n");
      out.write("                    <a href=\"RegisterPage.jsp\">Register</a>\n");
      out.write("                    <a href=\"LoginPage.jsp\">Log in</a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("\n");
      out.write("        <div class=\"content\">\n");
      out.write("            <h1>All Pizzas</h1> \n");
      out.write("            <div class=\"product-grid\">\n");
      out.write("                <div class=\"product-card\">\n");
      out.write("                    <img src=\"pizza1.jpg\" alt=\"Capricciosa\">\n");
      out.write("                    <h2>Capricciosa</h2>\n");
      out.write("                    <p class=\"price\">$70.00</p>\n");
      out.write("                    <p class=\"category\">Category: Standard</p>\n");
      out.write("                    <p class=\"description\">A normal pizza with a taste from the forest.</p>\n");
      out.write("                    <button class=\"add-button\">Add Pizza</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
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
