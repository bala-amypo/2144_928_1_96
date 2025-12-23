package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.EmployeeProfile;

public interface EmployeeProfileService {

    List<EmployeeProfile> getByTeam(String teamName);
}
