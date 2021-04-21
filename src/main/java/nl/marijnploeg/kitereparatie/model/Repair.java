package nl.marijnploeg.kitereparatie.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Entity
//Using joined table inheritance will enable a relationship between the super class and other entities. A repair will eventually be store in a single data table, but with different types.
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long repairID;

    @ManyToOne
    private Customer customerID;

    @OneToMany(mappedBy = "employeeID")
    private List<Employee> employeeList;

    @Enumerated(EnumType.STRING)
    private RepairType repairType;

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

    private Date buildYear;

    @Column(nullable = false)
    private String repairNote;
}
