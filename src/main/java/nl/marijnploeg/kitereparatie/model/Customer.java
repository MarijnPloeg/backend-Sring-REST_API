package nl.marijnploeg.kitereparatie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import nl.marijnploeg.kitereparatie.model.Authority.Authority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerID;

    @ManyToOne
    private Address addressID;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "repairID")
    private List<Repair> repairs;
}
