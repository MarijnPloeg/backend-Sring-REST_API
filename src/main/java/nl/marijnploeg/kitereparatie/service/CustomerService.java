package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.UserRoles.AppUser;
import nl.marijnploeg.kitereparatie.model.UserRoles.Customer;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import nl.marijnploeg.kitereparatie.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomerByAddressId(Address addressId) {
        return customerRepository.findCustomerByAddressID(addressId);
    }

    public Optional<AppUser> getCustomerByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public long saveCustomer(Customer customer) {
        AppUser newCustomer = appUserRepository.save(customer);
        return newCustomer.getAppUserId();
    }
}
