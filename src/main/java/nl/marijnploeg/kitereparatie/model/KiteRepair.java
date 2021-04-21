package nl.marijnploeg.kitereparatie.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class KiteRepair extends Repair {

    private boolean leadingEdge;

    private boolean canopy;

    private boolean leakage;

    private boolean strut;

    private String location;
}
