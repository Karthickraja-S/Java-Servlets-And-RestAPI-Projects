package com.example.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecureFilter implements Filter  {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String value = filterConfig.getInitParameter("Key");
        System.out.println("Init Params for Key attribute :: "+value);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // here we can authenticate the request based on role / do any logging process like how many request hitting server
        // check the request type and format for the mapped urls in web.xml
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String nameRequested = request.getParameter("sname");
        System.out.println(nameRequested);
        if(nameRequested!= null && nameRequested.toLowerCase().contains("karthi")) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("text/html");
            writer.println("Sorry Buddy! Request Rejected. I don't know whom you are..");
            writer.close();
        }
    }

    @Override
    public void destroy() {

    }
}
