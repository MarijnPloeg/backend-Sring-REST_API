package nl.marijnploeg.kitereparatie.model.RepairInstances;

import nl.marijnploeg.kitereparatie.model.Repair;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BarRepair extends Repair {

    private boolean lengthCheck;
}
