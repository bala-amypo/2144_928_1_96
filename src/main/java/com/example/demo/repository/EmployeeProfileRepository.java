import java.util.Optional;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    Optional<EmployeeProfile> findByEmployeeId(int employeeId);

    List<EmployeeProfile> findByTeamNameAndActiveTrue(String teamName);
}
