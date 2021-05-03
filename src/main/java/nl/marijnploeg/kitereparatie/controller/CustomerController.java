package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @CrossOrigin
    @GetMapping("/addressId/{addressId}")
    public ResponseEntity<Object> getCustomerByAddressId(@PathVariable Address addressId) {
        return ResponseEntity.ok().body(customerService.getCustomerByAddressId(addressId));
    }

    @CrossOrigin
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(customerService.getCustomerByEmail(email));
    }

}
