package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}
