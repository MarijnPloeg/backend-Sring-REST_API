package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.Customer;
import nl.marijnploeg.kitereparatie.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

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

    public List<Customer> getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    public long saveCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getCustomerID();
    }
}
