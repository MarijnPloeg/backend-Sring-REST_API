package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByFirstname(String firstName);

    List<Customer> findCustomerByLastname(String lastName);

    List<Customer> findCustomerByAddressID(Address addressId);

    List<Customer> findCustomerByEmail(String email);

}
