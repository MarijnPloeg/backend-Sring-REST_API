package nl.marijnploeg.kitereparatie.model.RepairInstances;

import nl.marijnploeg.kitereparatie.model.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "KITE")
public class KiteRepair extends Repair {

    private boolean leadingEdge;

    private boolean canopy;

    private boolean leakage;

    private boolean strut;

    private String location;

    public KiteRepair() {
    }

    public KiteRepair(long repairID, Customer customerID, List<Employee> employeeList, DeliveryOption deliveryOption, boolean expressRepair, boolean cleanProduct, String productImage, Brand brand, String model, int size, int buildYear, Date dateCreated, String repairNote, boolean leadingEdge, boolean canopy, boolean leakage, boolean strut, String location) {
        super(repairID, customerID, employeeList, deliveryOption, expressRepair, cleanProduct, productImage, brand, model, size, buildYear, dateCreated, repairNote);
        this.leadingEdge = leadingEdge;
        this.canopy = canopy;
        this.leakage = leakage;
        this.strut = strut;
        this.location = location;
    }

    public boolean isLeadingEdge() {
        return leadingEdge;
    }

    public void setLeadingEdge(boolean leadingEdge) {
        this.leadingEdge = leadingEdge;
    }

    public boolean isCanopy() {
        return canopy;
    }

    public void setCanopy(boolean canopy) {
        this.canopy = canopy;
    }

    public boolean isLeakage() {
        return leakage;
    }

    public void setLeakage(boolean leakage) {
        this.leakage = leakage;
    }

    public boolean isStrut() {
        return strut;
    }

    public void setStrut(boolean strut) {
        this.strut = strut;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
