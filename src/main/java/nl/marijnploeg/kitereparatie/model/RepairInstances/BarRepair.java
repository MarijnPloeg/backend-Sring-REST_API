package nl.marijnploeg.kitereparatie.model.RepairInstances;

import com.fasterxml.jackson.annotation.JsonTypeName;
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
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "BAR")
@JsonTypeName("Bar")
public class BarRepair extends Repair {
    private boolean lengthCheck;
}
