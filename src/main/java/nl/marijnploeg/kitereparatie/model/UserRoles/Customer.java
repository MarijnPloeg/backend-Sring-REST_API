package nl.marijnploeg.kitereparatie.model.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.Repair;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "CUSTOMER")
public class Customer extends AppUser {

    @ManyToOne
    private Address addressID;

    @OneToMany(mappedBy = "repairID")
    private List<Repair> repairs;

}
