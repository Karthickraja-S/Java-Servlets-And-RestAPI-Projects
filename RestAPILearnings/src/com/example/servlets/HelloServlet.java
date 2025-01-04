package com.example.servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        Map<String, String> cookieMap = null;
        Cookie[] cookie = request.getCookies();
        if(cookie != null) {
             cookieMap = Arrays
                    .stream(cookie)
                    .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
        }

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h1> Getting response from servlets ... </h1>");

        writer.println("<br> reqURI : "+reqURI);
        writer.println("<br> Servlet Path : "+servPath);
        writer.println("<br> context Path : "+contextPath);
        writer.println("<br> Query Str : "+queryStr);

        if(cookieMap != null) {
            writer.println("Cookie Map : " + cookieMap.getOrDefault("PrevSearches", "Empty"));
            Cookie cookieD = new Cookie("PrevSearches" , cookieMap.keySet().toString());
            response.addCookie(cookieD);
        }
        if(name != null && !name.isEmpty()) {
            writer.println("<h1> Hi "+name + " ..! </h1>");
        }
        writer.close();
    }
}
