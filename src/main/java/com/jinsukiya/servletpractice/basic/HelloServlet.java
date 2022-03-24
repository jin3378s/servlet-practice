package com.jinsukiya.servletpractice.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name","hi");

        request.getSession(true);
        String username = request.getParameter("username");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // message body
        response.getWriter().write("hello : " + username);
    }
}
