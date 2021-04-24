package nl.marijnploeg.kitereparatie.model.RepairInstances;

import nl.marijnploeg.kitereparatie.model.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "BAR")
public class BarRepair extends Repair {

    private boolean lengthCheck;

    public BarRepair() {
    }

    public BarRepair(long repairID, Customer customerID, List<Employee> employeeList, DeliveryOption deliveryOption, boolean expressRepair, boolean cleanProduct, String productImage, Brand brand, String model, int size, int buildYear, Date dateCreated, String repairNote, boolean lengthCheck) {
        super(repairID, customerID, employeeList, deliveryOption, expressRepair, cleanProduct, productImage, brand, model, size, buildYear, dateCreated, repairNote);
        this.lengthCheck = lengthCheck;
    }

    public boolean isLengthCheck() {
        return lengthCheck;
    }

    public void setLengthCheck(boolean lengthCheck) {
        this.lengthCheck = lengthCheck;
    }
}
