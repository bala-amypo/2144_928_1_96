package com.example.demo.service;

import java.util.List;

public interface CapacityAnalysisService {

    void analyze();

    List<String> getAlerts(String teamName);
}
