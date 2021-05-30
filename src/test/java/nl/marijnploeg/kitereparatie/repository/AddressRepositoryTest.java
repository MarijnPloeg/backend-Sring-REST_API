package nl.marijnploeg.kitereparatie.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {AddressRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testExistsAddressByAppUsers() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(33);
        address.setState("Noord Holland");

        Address address1 = new Address();
        address1.setPostalCode("2012 JN");
        address1.setStreetName("Westerhoutpark");
        address1.setAddressID(1L);
        address1.setCountry("Nederland");
        address1.setAppUsers(new ArrayList<AppUser>());
        address1.setCity("Haarlem");
        address1.setHouseNumber(10);
        address1.setState("Noord Holland");
        this.addressRepository.save(address);
        this.addressRepository.save(address1);
        assertFalse(this.addressRepository.existsAddressByAppUsers(1L));
    }
}

