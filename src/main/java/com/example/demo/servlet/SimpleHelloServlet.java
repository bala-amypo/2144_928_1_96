package com.example.demo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleHelloServlet extends HttpServlet {
    
    private String message = "Hello from servlet";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        if (config != null) {
            super.init(config);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_OK);
        
        PrintWriter out = response.getWriter();
        out.print(message);
        out.flush();
    }
}