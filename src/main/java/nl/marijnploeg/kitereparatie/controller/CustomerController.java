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


    @GetMapping("/addressId/{addressId}")
    public ResponseEntity<Object> getCustomerByAddressId(@PathVariable Address addressId) {
        return ResponseEntity.ok().body(customerService.getCustomerByAddressId(addressId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(customerService.getCustomerByEmail(email));
    }

//    @CrossOrigin
//    @PostMapping("/register")
//    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer ) {
//        long newId = customerService.saveCustomer(customer);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newId).toUri();
//
//        return ResponseEntity.created(location).body("New customer created on " + location);
//    }
}
