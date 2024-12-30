package com.example.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {

        // here we can get the form data / any path param from front end
        // and we can do our business logic here
        String name = request.getParameter("name");

        String reqURI = request.getRequestURI();
        String servPath = request.getServletPath();
        String contextPath = request.getContextPath();
        String queryStr = request.getQueryString();

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h1> Getting response from servlets ... </h1>");

        writer.println("<br> reqURI : "+reqURI);
        writer.println("<br> Servlet Path : "+servPath);
        writer.println("<br> context Path : "+contextPath);
        writer.println("<br> Query Str : "+queryStr);

        if(name != null && !name.isEmpty()) {
            writer.println("<h1> Hi "+name + " ..! </h1>");
        }
        writer.close();
    }
}
