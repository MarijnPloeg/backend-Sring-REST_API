package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.exception.EmailNotFoundException;
import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.model.Authority.Authority;
import nl.marijnploeg.kitereparatie.model.Customer;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import nl.marijnploeg.kitereparatie.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomerByFirstName(String firstname) {
        return customerRepository.findCustomerByFirstname(firstname);
    }

    public List<Customer> getCustomersByLastName(String lastName){
        return customerRepository.findCustomerByLastname(lastName);
    }

    public List<Customer> getCustomerByAddressId(Address addressId) {
        return customerRepository.findCustomerByAddressID(addressId);
    }

    public Optional<AppUser> getCustomerByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public long saveCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getCustomerID();
    }
}
