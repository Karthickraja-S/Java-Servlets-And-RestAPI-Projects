package com.example.servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {

        // here we can get the form data / any path param from front end
        // and we can do our business logic here
        String name = request.getParameter("name");

        String reqURI = request.getRequestURI();
        String servPath = request.getServletPath();
        String contextPath = request.getContextPath();
        String queryStr = request.getQueryString();

        // cookie can be helpful in storing some data in server and returning immediately by
        // not checking from backend ( like cache )
        // also cookie will be saved in browser as well as to server itself.
        // real usecase : rememberME option , search history
        Cookie[] cookies = request.getCookies();

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h1> Getting response from servlets ... </h1>");

        writer.println("<br> reqURI : "+reqURI);
        writer.println("<br> Servlet Path : "+servPath);
        writer.println("<br> context Path : "+contextPath);
        writer.println("<br> Query Str : "+queryStr);

        if(name != null && !name.isEmpty()) {
            writer.println("<h1> Hi "+name + " ..! </h1>");
            if(cookies != null) {
                String data = "";
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equalsIgnoreCase("PreviousHistory")) {
                        data = URLDecoder.decode(cookie.getValue() , "UTF-8");
                        writer.println("Previous History : "+ data);
                    }
                }
                data = URLEncoder.encode( data + name + " , ", "UTF-8" ); // to avoid RFC 6265 compliant implementation
                Cookie ck = new Cookie("PreviousHistory" ,data);
                response.addCookie(ck);
            }
        }
        writer.close();
    }
}
