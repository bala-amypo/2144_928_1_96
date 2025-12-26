package com.example.demo;

import com.example.demo.servlet.SimpleHelloServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    // Register the SimpleHelloServlet manually
    @Bean
    public ServletRegistrationBean<SimpleHelloServlet> simpleHelloServletRegistrationBean() {
        ServletRegistrationBean<SimpleHelloServlet> registrationBean = new ServletRegistrationBean<>(new SimpleHelloServlet(), "/servlet/hello");
        registrationBean.setLoadOnStartup(1); // Ensure it's initialized
        return registrationBean;
    }
}