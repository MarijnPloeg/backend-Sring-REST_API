package nl.marijnploeg.kitereparatie.model.RepairInstances;

import nl.marijnploeg.kitereparatie.model.Repair;

import javax.persistence.Entity;

@Entity
public class WetsuitRepair extends Repair {

    private boolean zipper;
    private int damageSizeCM;
}

