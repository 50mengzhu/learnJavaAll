package me.learn.mica.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 在实现的额 tomcat中可以通过使用 web.xml或者 @WebServlet等注解的方式实现同样的功能
 */
@WebFilter({"/HelloServlet", "/lifestyle"})
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String name = filterConfig.getInitParameter("name");
        System.out.println(name);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
        String name = servletRequest.getParameter("name");
        if ("liusw".equals(name)) {
            servletResponse.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = servletResponse.getWriter();
            String content = "刘仕伟是屌毛";
            String docType = "<!DOCTYPE html>\n" + "<html>\n" +
                    "<head><title> hello </title></head>\n"+
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">  " + content +  "</h1>\n";
            writer.println(docType);
            writer.println("</body></html>");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
