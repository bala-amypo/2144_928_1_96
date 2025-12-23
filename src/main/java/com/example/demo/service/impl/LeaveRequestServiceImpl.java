package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
// Fix 9, 10, 31: Correct model imports
import com.example.demo.model.EmployeeProfile; 
import com.example.demo.model.LeaveRequest; 
// ... (omitting other imports for brevity)

@Service
@RequiredArgsConstructor
// Fix 30: Must implement the required method
public class LeaveRequestServiceImpl implements LeaveRequestService {
    // ... (fields)

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        // ... (creation logic)
        return null; // Placeholder
    }
    
    // Fix 30: Implementation of the required abstract method
    @Override 
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile employee = employeeProfileRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        
        List<LeaveRequest> requests = leaveRequestRepository.findByEmployee(employee);
        
        // Map LeaveRequest entities to LeaveRequestDto and return
        return requests.stream()
                // Assume you have a mapper utility or manually map fields
                .map(this::convertToDto) 
                .collect(Collectors.toList());
    }

    // ... other methods
    private LeaveRequestDto convertToDto(LeaveRequest request) { 
        return LeaveRequestDto.builder()
            .id(request.getId())
            .employeeId(request.getEmployee().getId())
            // ... other fields
            .build(); 
    }
}