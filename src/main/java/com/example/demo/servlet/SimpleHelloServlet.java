package com.example.demo.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleHelloServlet extends HttpServlet {

    private String message;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.message = "Hello from SimpleHelloServlet";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.write(message);
        out.flush();
    }

    // Getter added for TestNG reflection checks
    public String getMessage() {
        return message;
    }
}
