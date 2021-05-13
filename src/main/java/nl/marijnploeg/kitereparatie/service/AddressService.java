package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.exception.ApiExceptions.ApiRequestException;
import nl.marijnploeg.kitereparatie.exception.DatabaseErrorException;
import nl.marijnploeg.kitereparatie.exception.RecordNotFoundException;
import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(long id) {
        if (addressRepository.existsById(id)) {
            return addressRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public Address getAddressByUserId(long id) {
        if (addressRepository.existsAddressByAppUsers(id)) {
            return addressRepository.findAddressByAppUsers(id);
        } else {
            throw new ApiRequestException("Deze gebruiker heeft nog geen adres!");
        }
    }

    public void deleteAddress(long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long saveAddress(AppUser appUser, Address address) {
        Address newAddress = addressRepository.save(address);
        newAddress.addToList(appUser);
        return newAddress.getAddressID();
    }

    public void updateAddress(long id, Address address) {
        if (addressRepository.existsById(id)) {
            try {
                Address existingAddress = addressRepository.findById(id).orElse(null);
                assert existingAddress != null;
                existingAddress.setCity(address.getCity());
                existingAddress.setCountry(address.getCountry());
                existingAddress.setHouseNumber(address.getHouseNumber());
                existingAddress.setPostalCode(address.getPostalCode());
                existingAddress.setStreetName(address.getStreetName());
                existingAddress.setState(address.getState());
                addressRepository.save(existingAddress);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }

//  DTO / Payload voor custom methodes
}
