package nl.marijnploeg.kitereparatie.model.UserRoles;

import nl.marijnploeg.kitereparatie.model.UserRoles.AppUser;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MANAGER")
public class Manager extends AppUser {


}
