package nl.marijnploeg.kitereparatie.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BarRepair extends Repair {

    private boolean lengthCheck;
}
