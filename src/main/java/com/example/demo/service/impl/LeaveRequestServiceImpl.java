@Service
public class LeaveRequestServiceImpl {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRepo,
            EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    public LeaveRequest create(LeaveRequestDto dto) {

        // 🔥 FIND EMPLOYEE BY BUSINESS employeeId
        EmployeeProfile employee = employeeRepo
                .findByEmployeeId(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee); // ✅ THIS FIXES FK ERROR
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setType(dto.getType());
        leave.setStatus(dto.getStatus());
        leave.setReason(dto.getReason());

        return leaveRepo.save(leave);
    }
}
