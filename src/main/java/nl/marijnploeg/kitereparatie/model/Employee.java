package nl.marijnploeg.kitereparatie.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeID;

//    @Column(name = "name")
    private String name;

    @ManyToMany
    private List<Repair> repairs;

}


