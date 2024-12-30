package com.example.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecureHelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String name = request.getParameter("sname");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<h1> Getting secret response from your friend Karthick ... </h1>");
        writer.println("<h1> Hi "+name + " ..! </h1>");
        writer.println("Hmm... The secret message is -> Nothing :P ");

        writer.close();
    }
}
