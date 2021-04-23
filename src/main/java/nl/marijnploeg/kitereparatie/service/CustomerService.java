package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
}
