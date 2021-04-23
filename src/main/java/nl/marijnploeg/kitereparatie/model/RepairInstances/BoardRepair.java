package nl.marijnploeg.kitereparatie.model.RepairInstances;

import nl.marijnploeg.kitereparatie.model.BoardType;
import nl.marijnploeg.kitereparatie.model.Repair;

import javax.persistence.*;

@Entity
public class BoardRepair extends Repair {

    @Enumerated(EnumType.STRING)
    private BoardType boardType;


}
