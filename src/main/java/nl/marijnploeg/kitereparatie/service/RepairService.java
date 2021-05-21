package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.exception.DatabaseErrorException;
import nl.marijnploeg.kitereparatie.exception.RecordNotFoundException;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.Repair;
import nl.marijnploeg.kitereparatie.model.Enums.RepairType;
import nl.marijnploeg.kitereparatie.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    public List<Repair> getRepairs() {
        return repairRepository.findAll();
    }

    public List<Repair> getRepairsByBrand(long brandId) {
        return repairRepository.findRepairsByBrand(brandId);
    }

    public List<Repair> getRepairsByRepairType(RepairType repairType) {
        return repairRepository.findRepairsByRepairType(repairType);
    }

    public List<Repair> getRepairsByModel(String model) {
        return repairRepository.findRepairsByModel(model);
    }

    public List<Repair> findRepairsByBuildYear(int buildYear) {
        return repairRepository.findRepairsByBuildYear(buildYear);
    }

    public List<Repair> findRepairsByDateCreated(Date dateCreated) {
        return repairRepository.findRepairsByDateCreated(dateCreated);
    }

    public List<Repair> getRepairsByAppUserId(AppUser appUserId) {
        return repairRepository.findRepairsByAppUserId(appUserId);
    }

    public long saveRepair(Repair repair) {
        Repair newRepair = repairRepository.save(repair);
        return newRepair.repairID;
    }

    public void updateRepair(long repairId, Repair repair) {
        if (repairRepository.existsById(repairId)) {
            try {
                Repair existingRepair = repairRepository.findById(repairId).orElse(null);
                assert existingRepair != null;
                existingRepair.setBrand(repair.getBrand());
                existingRepair.setBuildYear(repair.getBuildYear());
                existingRepair.setAppUserId(repair.getAppUserId());
                existingRepair.setCleanProduct(repair.isCleanProduct());
                existingRepair.setExpressRepair(repair.isExpressRepair());
                existingRepair.setModel(repair.getModel());
                existingRepair.setRepairNote(repair.getRepairNote());
                repairRepository.save(existingRepair);
            } catch (Exception exception) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }
}
