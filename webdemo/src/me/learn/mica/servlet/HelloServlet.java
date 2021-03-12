package me.learn.mica.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 以下是没有涉及到 filter的整体的请求流程
 *
 * 当接收到用户请求的时候，然后由 servlet container（这里是 tomcat）将进行分发到对应的 servlet上
 * servlet接收到请求之后根据请求的类型对用户请求进行处理，并封装 Response返回给用户
 * 参考 <a href="https://www.runoob.com/jsp/jsp-architecture.html">菜鸟教程</a> 对以上过程的描述
 *
 * 自己实现一个 servlet的
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {
        if (true) {
            throw new IOException("jjjj");
        }
        resp.setCharacterEncoding("GBK");
        resp.getWriter().write("Hello world!");
    }

    @Override
    protected void doPost(HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
