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

    @Column(nullable = false)
    private String brandName;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BrandType brandType;

    public Brand() {
    }

    public Brand(long brandID, String brandName, BrandType brandType) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandType = brandType;
    }

    public long getBrandID() {
        return brandID;
    }
    public void setBrandID(long brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BrandType getBrandType() {
        return brandType;
    }
    public void setBrandType(BrandType brandType) {
        this.brandType = brandType;
    }

}
