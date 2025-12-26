package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestDto {
    private Long id;
    private String employeeId; // Changed to String to match EmployeeProfile's employeeId
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String status;
    private String reason;
}