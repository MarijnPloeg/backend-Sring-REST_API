package nl.marijnploeg.kitereparatie.model.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.model.Repair;
import nl.marijnploeg.kitereparatie.model.UserRoles.AppUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "EMPLOYEE")
public class Employee extends AppUser {

        private String name;

    @ManyToMany
    private List<Repair> repairs;

}


