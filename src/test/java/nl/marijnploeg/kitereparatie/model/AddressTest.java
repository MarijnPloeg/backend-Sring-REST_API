package nl.marijnploeg.kitereparatie.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AddressTest {
    @Test
    public void testSetAddressID() {
        Address address = new Address();
        address.setAddressID(1L);
        assertEquals(1L, address.getAddressID());
    }

    @Test
    public void testSetCity() {
        Address address = new Address();
        address.setCity("Oxford");
        assertEquals("Oxford", address.getCity());
    }

    @Test
    public void testSetCountry() {
        Address address = new Address();
        address.setCountry("Country");
        assertEquals("Country", address.getCountry());
    }

    @Test
    public void testSetHouseNumber() {
        Address address = new Address();
        address.setHouseNumber(10);
        assertEquals(10, address.getHouseNumber());
    }

    @Test
    public void testSetPostalCode() {
        Address address = new Address();
        address.setPostalCode("Postal Code");
        assertEquals("Postal Code", address.getPostalCode());
    }

    @Test
    public void testSetState() {
        Address address = new Address();
        address.setState("MD");
        assertEquals("MD", address.getState());
    }

    @Test
    public void testSetStreetName() {
        Address address = new Address();
        address.setStreetName("Street Name");
        assertEquals("Street Name", address.getStreetName());
    }
}

