package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.UserRoles.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
