package nl.marijnploeg.kitereparatie.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressID;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = false)
    private List<AppUser> appUsers;

    @Column(name = "street_name", nullable = false, length = 75)
    private String streetName;

    @Column(name = "house_number", nullable = false, length = 10)
    private int houseNumber;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 12)
    private String postalCode;

    @Column(nullable = true, length = 50)
    private String State;

    @Column(nullable = true, length = 200)
    private String country;

    public void addToList(AppUser appUser) {
        appUsers.add(appUser);
    }
}
