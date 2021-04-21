package nl.marijnploeg.kitereparatie.model;

import javax.persistence.*;

@Entity
public class BoardRepair extends Repair {

    @Enumerated(EnumType.STRING)
    private BoardType boardType;


}
