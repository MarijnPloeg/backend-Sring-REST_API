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

    @Column(nullable = false, length = 10)
    private int houseNumber;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 12)
    private String postalCode;

    @Column(nullable = true, length = 50)
    private String State;

    @Column(nullable = false, length = 200)
    private String country;

    public Address() {

    }

    public Address(String streetName, int houseNumber, String city, String postalCode, String state, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
        State = state;
        this.country = country;
    }

    public long getAddressID() {
        return addressID;
    }
    public void setAddressID(long addressID) {
        this.addressID = addressID;
    }

    public List<Customer> getCustomerID() {
        return customerID;
    }
    public void setCustomerID(List<Customer> customerID) {
        this.customerID = customerID;
    }

    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return State;
    }
    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
