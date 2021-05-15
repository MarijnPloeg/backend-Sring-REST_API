package nl.marijnploeg.kitereparatie.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "appUserId")
    private List<AppUser> appUsers = new ArrayList<>();


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

    public void addUser(AppUser appUser) {
        appUsers.add(appUser);
        appUser.setAddress(this);
    }

    public void removeUser(AppUser appUser) {
        appUsers.remove(appUser);
        appUser.setAddress(null);
    }

}
