package nl.marijnploeg.kitereparatie.model;

import javax.persistence.Entity;

@Entity
public class WetsuitRepair extends Repair{

    private boolean zipper;
    private int damageSizeCM;
}

