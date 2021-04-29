package nl.marijnploeg.kitereparatie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeID;

    private String name;

    @ManyToMany
    private List<Repair> repairs;

}


