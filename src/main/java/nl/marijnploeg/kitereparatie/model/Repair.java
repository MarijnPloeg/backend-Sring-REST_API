package nl.marijnploeg.kitereparatie.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.converter.RepairTypeConverter;
import nl.marijnploeg.kitereparatie.model.Enums.BoardType;
import nl.marijnploeg.kitereparatie.model.Enums.DeliveryOption;
import nl.marijnploeg.kitereparatie.model.Enums.RepairType;
import nl.marijnploeg.kitereparatie.model.RepairInstances.BarRepair;
import nl.marijnploeg.kitereparatie.model.RepairInstances.BoardRepair;
import nl.marijnploeg.kitereparatie.model.RepairInstances.KiteRepair;
import nl.marijnploeg.kitereparatie.model.RepairInstances.WetsuitRepair;
import nl.marijnploeg.kitereparatie.model.UserRoles.AppUser;
import nl.marijnploeg.kitereparatie.model.UserRoles.Customer;
import nl.marijnploeg.kitereparatie.model.UserRoles.Employee;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BarRepair.class, name = "Bar"),
        @JsonSubTypes.Type(value = BoardRepair.class, name = "Board"),
        @JsonSubTypes.Type(value = KiteRepair.class, name = "Kite"),
        @JsonSubTypes.Type(value = WetsuitRepair.class, name = "Wetsuit")
})
public abstract class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long repairID;

    @ManyToOne
    private AppUser customerID;

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
