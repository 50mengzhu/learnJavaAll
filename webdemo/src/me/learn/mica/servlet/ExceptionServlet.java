package me.learn.mica.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(urlPatterns = "/errorException", name = "errorHandler")
public class ExceptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer errorCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        if (Objects.isNull(servletName)) {
            servletName = "Unknown";
        }
        String reqUrl = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (Objects.isNull(reqUrl)) {
            reqUrl = "Unknown url";
        }
        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        String title = "菜鸟教程 Error/Exception 信息";

        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n");
        out.println("<h1>菜鸟教程异常信息实例演示</h1>");
        if (throwable == null && errorCode == null){
            out.println("<h2>错误信息丢失</h2>");
            out.println("请返回 <a href=\"" +
                    resp.encodeURL("http://localhost:8080/") +
                    "\">主页</a>。");
        }else if (errorCode != null) {
            out.println("错误代码 : " + errorCode);
        }else{
            out.println("<h2>错误信息</h2>");
            out.println("Servlet Name : " + servletName +
                    "</br></br>");
            out.println("异常类型 : " +
                    throwable.getClass( ).getName( ) +
                    "</br></br>");
            out.println("请求 URI: " + reqUrl +
                    "<br><br>");
            out.println("异常信息: " +
                    throwable.getMessage( ));
        }
        out.println("</body>");
        out.println("</html>");
    }
}
