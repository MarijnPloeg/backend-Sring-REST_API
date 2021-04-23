package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.Customer;
import nl.marijnploeg.kitereparatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @GetMapping("/{firstname}")
    public ResponseEntity<Object> getCustomerByFirstName(@PathVariable String firstname) {
        return ResponseEntity.ok().body(customerService.getCustomerByFirstName(firstname));
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<Object> getCustomerByLastName(@PathVariable String lastname) {
        return ResponseEntity.ok().body(customerService.getCustomersByLastName(lastname));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Object> getCustomerByAddressId(@PathVariable Address addressId) {
        return ResponseEntity.ok().body(customerService.getCustomerByAddressId(addressId));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(customerService.getCustomerByEmail(email));
    }

    @PostMapping("")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) {
        long newId = customerService.saveCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("New customer created on " + location);
    }
}
