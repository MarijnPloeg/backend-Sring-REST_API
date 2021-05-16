package nl.marijnploeg.kitereparatie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandid")
    private long brandID;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "brand_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BrandType brandType;
}
