package nl.marijnploeg.kitereparatie.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.converter.RepairTypeConverter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long repairID;

    @ManyToOne
    private Customer customerID;

    @Convert(converter = RepairTypeConverter.class)
    @Column(name = "repair_type")
    private RepairType repairType;

    @OneToMany
    private List<Employee> employeeList;

    @Enumerated(EnumType.STRING)
    private DeliveryOption deliveryOption;

    private boolean expressRepair;

    private boolean cleanProduct;

    @Column(length = 64)
    private String productImage;

    @OneToOne
    private Brand brand;

    private String model;

    private int size;

    private int buildYear;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(nullable = false)
    private String repairNote;

    public Repair() {
    }

    public Repair(long repairID, Customer customerID, List<Employee> employeeList, DeliveryOption deliveryOption, boolean expressRepair, boolean cleanProduct, String productImage, Brand brand, String model, int size, int buildYear, Date dateCreated, String repairNote) {
        this.repairID = repairID;
        this.customerID = customerID;
        this.employeeList = employeeList;
        this.deliveryOption = deliveryOption;
        this.expressRepair = expressRepair;
        this.cleanProduct = cleanProduct;
        this.productImage = productImage;
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.buildYear = buildYear;
        this.dateCreated = dateCreated;
        this.repairNote = repairNote;
    }

    public long getRepairID() {
        return repairID;
    }

    public void setRepairID(long repairID) {
        this.repairID = repairID;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public boolean isExpressRepair() {
        return expressRepair;
    }

    public void setExpressRepair(boolean expressRepair) {
        this.expressRepair = expressRepair;
    }

    public boolean isCleanProduct() {
        return cleanProduct;
    }

    public void setCleanProduct(boolean cleanProduct) {
        this.cleanProduct = cleanProduct;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRepairNote() {
        return repairNote;
    }

    public void setRepairNote(String repairNote) {
        this.repairNote = repairNote;
    }
}
