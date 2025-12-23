@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String status;
    private String reason;
}
