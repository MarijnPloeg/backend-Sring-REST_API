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

    @ManyToOne
    private Address addressID;

    @OneToMany(mappedBy = "repairID")
    private List<Repair> repairs;
}
