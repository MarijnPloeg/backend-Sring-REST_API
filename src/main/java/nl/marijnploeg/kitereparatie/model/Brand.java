package nl.marijnploeg.kitereparatie.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long brandID;

    @Enumerated(EnumType.ORDINAL)
    private Brands brand;
}
