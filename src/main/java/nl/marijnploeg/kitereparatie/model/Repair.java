package nl.marijnploeg.kitereparatie.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.converter.RepairTypeConverter;
import nl.marijnploeg.kitereparatie.model.Enums.DeliveryOption;
import nl.marijnploeg.kitereparatie.model.Enums.RepairType;
import nl.marijnploeg.kitereparatie.model.UserRoles.Customer;
import nl.marijnploeg.kitereparatie.model.UserRoles.Employee;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
