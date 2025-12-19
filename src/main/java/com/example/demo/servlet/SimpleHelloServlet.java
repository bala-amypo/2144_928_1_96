package com.example.demo.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SimpleHelloServlet extends HttpServlet {

    private String message = "Hello from servlet";

    @Override
    public void init(ServletConfig config) {
        try {
            super.init(config);
        } catch (Exception ignored) {}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain");
        resp.getWriter().write(message);
    }
}
