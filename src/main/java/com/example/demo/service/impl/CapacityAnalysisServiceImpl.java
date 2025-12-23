package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
// Fix 5, 6, 33, 37: Correct model imports
import com.example.demo.model.CapacityAlert; 
import com.example.demo.model.TeamCapacityConfig; 
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository; // Fix 7, 8: Correct repository import
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
// ... (omitting other imports for brevity)

@Service
@RequiredArgsConstructor
// Fix 22, 23: Must implement the three-argument method
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository teamCapacityConfigRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final CapacityAlertRepository capacityAlertRepository;

    @Override // Fix 23: Must have @Override and match interface
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        // ... (validation logic)
        
        // Fix 32: Class must be imported correctly
        TeamCapacityConfig rule = teamCapacityConfigRepository.findByTeamName(teamName)
            .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found for team: " + teamName));

        // ... (date range logic)

        for (LocalDate date : days) {
            // Fix 34: findApprovedOnDate method must exist on the repository
            long leavesOnLeave = leaveRequestRepository.findApprovedOnDate(date)
                // ... (filtering logic for teamName)
                
            // ... (capacity calculation)
            
            // Fix 36: Accessing CapacityAlert correctly
            CapacityAlert alert = CapacityAlert.builder()
                // ... (alert details)
                .build();
            
            // ... (alert saving logic)
        }

        // Fix 38: Static builder access must be correct for the DTO
        return CapacityAnalysisResultDto.builder()
            .risky(risky)
            .capacityByDate(capacityByDate)
            .build();
    }
}