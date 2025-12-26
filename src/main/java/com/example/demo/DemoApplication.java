package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.example.demo.servlet.SimpleHelloServlet;

@SpringBootApplication
public class DemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @Bean
    public ServletRegistrationBean<SimpleHelloServlet> helloServlet() {
        ServletRegistrationBean<SimpleHelloServlet> bean = new ServletRegistrationBean<>(
            new SimpleHelloServlet(), "/hello");
        bean.setLoadOnStartup(1);
        return bean;
    }
}