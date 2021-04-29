package nl.marijnploeg.kitereparatie.model.RepairInstances;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.model.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue(value = "KITE")
public class KiteRepair extends Repair {

    private boolean leadingEdge;

    private boolean canopy;

    private boolean leakage;

    private boolean strut;

    private String location;
}
