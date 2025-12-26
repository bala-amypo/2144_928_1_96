package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class EmployeeProfileService {
    public String getEmployeeName(Long id) {
        return "Test Employee";
    }
}
