package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.UserRoles.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
