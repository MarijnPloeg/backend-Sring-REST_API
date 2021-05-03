package nl.marijnploeg.kitereparatie.model.RepairInstances;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.model.*;
import nl.marijnploeg.kitereparatie.model.Enums.BoardType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "BOARD")
public class BoardRepair extends Repair {

    @Enumerated(EnumType.STRING)
    private BoardType boardType;
}
