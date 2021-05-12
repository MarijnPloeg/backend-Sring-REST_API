package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Repair;
import nl.marijnploeg.kitereparatie.model.Enums.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    List<Repair> findRepairsByBrand(long brandId);

    List<Repair> findRepairsByRepairType(RepairType repairType);

    List<Repair> findRepairsByModel(String model);

    List<Repair> findRepairsByBuildYear(int buildYear);

    List<Repair> findRepairsByDateCreated(Date dateCreated);

    List<Repair> findRepairsByAppUserId(long customerId);

}
