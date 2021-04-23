package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.)
    }
}
