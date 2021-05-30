package nl.marijnploeg.kitereparatie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.marijnploeg.kitereparatie.exception.ApiExceptions.ApiRequestException;

import nl.marijnploeg.kitereparatie.exception.RecordNotFoundException;
import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.repository.AddressRepository;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressService.class})
@ExtendWith(SpringExtension.class)
public class AddressServiceTest {
    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Test
    public void testGetAddresses() {
        ArrayList<Address> addressList = new ArrayList<Address>();
        when(this.addressRepository.findAll()).thenReturn(addressList);
        List<Address> actualAddresses = this.addressService.getAddresses();
        assertSame(addressList, actualAddresses);
        assertTrue(actualAddresses.isEmpty());
        verify(this.addressRepository).findAll();
    }

    @Test
    public void testGetAddressById() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        Optional<Address> ofResult = Optional.of(address);
        when(this.addressRepository.findById(any())).thenReturn(ofResult);
        when(this.addressRepository.existsById(any())).thenReturn(true);
        assertSame(address, this.addressService.getAddressById(123L));
        verify(this.addressRepository).findById(any());
        verify(this.addressRepository).existsById(any());
    }

    @Test
    public void testGetAddressById2() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        Optional<Address> ofResult = Optional.of(address);
        when(this.addressRepository.findById(any())).thenReturn(ofResult);
        when(this.addressRepository.existsById(any())).thenReturn(false);
        assertThrows(RecordNotFoundException.class, () -> this.addressService.getAddressById(123L));
        verify(this.addressRepository).existsById(any());
    }

    @Test
    public void testGetAddressByUserId() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        when(this.addressRepository.findAddressByAppUsers(anyLong())).thenReturn(address);
        when(this.addressRepository.existsAddressByAppUsers(anyLong())).thenReturn(true);
        assertSame(address, this.addressService.getAddressByUserId(123L));
        verify(this.addressRepository).findAddressByAppUsers(anyLong());
        verify(this.addressRepository).existsAddressByAppUsers(anyLong());
    }

    @Test
    public void testGetAddressByUserId2() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        when(this.addressRepository.findAddressByAppUsers(anyLong())).thenReturn(address);
        when(this.addressRepository.existsAddressByAppUsers(anyLong())).thenReturn(false);
        assertThrows(ApiRequestException.class, () -> this.addressService.getAddressByUserId(123L));
        verify(this.addressRepository).existsAddressByAppUsers(anyLong());
    }

    @Test
    public void testDeleteAddress() {
        doNothing().when(this.addressRepository).deleteById(any());
        when(this.addressRepository.existsById(any())).thenReturn(true);
        this.addressService.deleteAddress(123L);
        verify(this.addressRepository).deleteById(any());
        verify(this.addressRepository).existsById(any());
    }

    @Test
    public void testDeleteAddress2() {
        doNothing().when(this.addressRepository).deleteById(any());
        when(this.addressRepository.existsById(any())).thenReturn(false);
        assertThrows(RecordNotFoundException.class, () -> this.addressService.deleteAddress(123L));
        verify(this.addressRepository).existsById(any());
    }

    @Test
    public void testSaveAddress() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        when(this.addressRepository.save(any())).thenReturn(address);
        AppUser appUser = new AppUser("Marijn", "Ploeg", "marijnploeg@gmail.com", "password", AppUserRole.CUSTOMER);
        assertEquals(1L, this.addressService.saveAddress(appUser, new Address()));
        verify(this.addressRepository).save(any());
        Address address1 = appUser.getAddress();
        assertSame(address, address1);
        assertEquals(1, address1.getAppUsers().size());
    }

    @Test
    public void testSaveAddress2() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");
        when(this.addressRepository.save(any())).thenReturn(address);
        AppUser appUser = mock(AppUser.class);
        doNothing().when(appUser).setAddress(any());
        assertEquals(1L, this.addressService.saveAddress(appUser, new Address()));
        verify(this.addressRepository).save(any());
        verify(appUser).setAddress(any());
    }
}

