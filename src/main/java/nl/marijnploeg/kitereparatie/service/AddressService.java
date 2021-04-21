package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddresses();
    Address getAddressById(long id);
    void deleteAddress(long id);
    long saveAddress(Address address);
    void updateAddress(long id, Address address);
    
}
