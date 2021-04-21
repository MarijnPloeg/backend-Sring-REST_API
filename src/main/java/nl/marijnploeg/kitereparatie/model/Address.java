package nl.marijnploeg.kitereparatie.model;

import javax.persistence.*;
import java.sql.Array;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressID;

    @OneToMany(mappedBy = "addressID")
    private List<Customer> customerID;

    @Column(nullable = false, length = 75)
    private String streetName;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 12)
    private String postalCode;

    @Column(nullable = true, length = 50)
    private String State;

    @Column(nullable = false, length = 200)
    private String country;

    public Address(int addressID, Array customerID, String streetName, String city, String postalCode, String state, String country) {
    }

    public Address() {

    }
}
