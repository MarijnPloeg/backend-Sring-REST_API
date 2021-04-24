package nl.marijnploeg.kitereparatie.model.RepairInstances;

import nl.marijnploeg.kitereparatie.model.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "WETSUIT")
public class WetsuitRepair extends Repair {

    private boolean zipper;
    private int damageSizeCM;

    public WetsuitRepair() {
    }

    public WetsuitRepair(long repairID, Customer customerID, List<Employee> employeeList, DeliveryOption deliveryOption, boolean expressRepair, boolean cleanProduct, String productImage, Brand brand, String model, int size, int buildYear, Date dateCreated, String repairNote, boolean zipper, int damageSizeCM) {
        super(repairID, customerID, employeeList, deliveryOption, expressRepair, cleanProduct, productImage, brand, model, size, buildYear, dateCreated, repairNote);
        this.zipper = zipper;
        this.damageSizeCM = damageSizeCM;
    }

    public boolean isZipper() {
        return zipper;
    }

    public void setZipper(boolean zipper) {
        this.zipper = zipper;
    }

    public int getDamageSizeCM() {
        return damageSizeCM;
    }

    public void setDamageSizeCM(int damageSizeCM) {
        this.damageSizeCM = damageSizeCM;
    }
}

