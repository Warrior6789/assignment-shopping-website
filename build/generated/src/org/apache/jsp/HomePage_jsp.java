package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dto.Account;
import dto.ProductViewDTO;
import dto.Products;
import java.util.List;

public final class HomePage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style>\n");
      out.write("            body{\n");
      out.write("                margin: 0;\n");
      out.write("                background-color: #222;\n");
      out.write("                color: #fff;\n");
      out.write("            }\n");
      out.write("            header{\n");
      out.write("                background-color: #6495ED;\n");
      out.write("                padding: 10px 0;\n");
      out.write("                border-bottom: 1px solid white\n");
      out.write("            }\n");
      out.write("            nav{\n");
      out.write("                display:flex;\n");
      out.write("                justify-content: space-between;\n");
      out.write("            }\n");
      out.write("            nav a {\n");
      out.write("\n");
      out.write("                text-decoration: none;\n");
      out.write("                margin: 0 15px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            button[type=\"submit\"]{\n");
      out.write("                background-color: #4CAF50;\n");
      out.write("                color:white;\n");
      out.write("                padding: 6px 10px;\n");
      out.write("            }\n");
      out.write("            .content {\n");
      out.write("                padding: 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            h1 {\n");
      out.write("                margin-bottom: 20px;\n");
      out.write("            }\n");
      out.write("            table {\n");
      out.write("                width: 100%;\n");
      out.write("                border-collapse: collapse;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            table, th, td {\n");
      out.write("                border-bottom: 1px solid #ddd;\n");
      out.write("                padding: 8px;\n");
      out.write("                text-align: left;\n");
      out.write("            }\n");
      out.write("            th {\n");
      out.write("                background-color: #333;\n");
      out.write("            }\n");
      out.write("            .search_create_content {\n");
      out.write("                display: flex; \n");
      out.write("                justify-content: space-between; \n");
      out.write("                align-items: center; \n");
      out.write("                margin-bottom: 10px;\n");
      out.write("            }  \n");
      out.write("            \n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            List<ProductViewDTO> list = (List<ProductViewDTO>) request.getAttribute("list");
            HttpSession sessionAcc = request.getSession();
            Account acc = (Account) sessionAcc.getAttribute("acc");
            String type = "User";
        
      out.write("\n");
      out.write("        ");

            if (list != null) {
        
      out.write("\n");
      out.write("        <header>\n");
      out.write("            <nav>\n");
      out.write("                <div>\n");
      out.write("                    <a>PizzaStore</a>\n");
      out.write("                    <a>Pizzas</a>\n");
      out.write("                    <a href=\"MainServlet?action=viewcate\">Categories</a>\n");
      out.write("                    <a>Reviews</a>\n");
      out.write("                    <a>Orders</a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div>\n");
      out.write("                    ");

                        if (acc.getType() == 1) {
                            type = "Admin";
                        }
                    
      out.write("\n");
      out.write("                    <a>");
      out.print(type);
      out.write("Page</a>\n");
      out.write("                    <span>Welcome,");
      out.print(type);
      out.write("</span>\n");
      out.write("                    <a href=\"LogoutServlet\">Log Out</a>  \n");
      out.write("                </div>\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("        <div class=\"content\">\n");
      out.write("            <h1>Pizzas</h1>\n");
      out.write("            <div class=\"search_create_content\">\n");
      out.write("                <form  action=\"MainServlet\" method=\"post\">\n");
      out.write("                    <input type=\"text\" name=\"value\" />\n");
      out.write("                    <button type=\"submit\" name=\"action\" value=\"search\">Search</button>\n");
      out.write("                </form>\n");
      out.write("\n");
      out.write("                <form action=\"MainServlet\" method=\"post\">\n");
      out.write("                    <button type=\"submit\" name=\"action\" value=\"viewcreatepage\">Create New</button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");

                String mess = (String) request.getAttribute("mess");
                if (mess != null) {
            
      out.write("\n");
      out.write("            <p>");
      out.print( mess);
      out.write("</p>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("            <table>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Name</th>\n");
      out.write("                        <th>Price</th>\n");
      out.write("                        <th>Description</th>\n");
      out.write("                        <th>ImageUrl</th>\n");
      out.write("                        <th>IsPizzaOfTheWeek</th>\n");
      out.write("                        <th>Category</th>\n");
      out.write("                        <th></th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                ");

                    for (ProductViewDTO p : list) {
                
      out.write("\n");
      out.write("                <tbody>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( p.getProductName());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( p.getUnitPrice());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( p.getDescription());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( p.getProductImage());
      out.write("</td>\n");
      out.write("                        <td><input type=\"checkbox\" name=\"ispizzaoftheweek\"/></td>\n");
      out.write("                        <td>");
      out.print( p.getCategoryName());
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <a href=\"MainServlet?action=edit&id=");
      out.print( p.getProductId());
      out.write("\">Edit|</a>\n");
      out.write("                            <a href=\"MainServlet?action=detail&id=");
      out.print( p.getProductId());
      out.write("\">Details|</a>\n");
      out.write("                            <a href=\"MainServlet?action=delete&id=");
      out.print( p.getProductId());
      out.write("\">Delete</a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                </tbody>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </table> \n");
      out.write("        </div>\n");
      out.write("\n");
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
