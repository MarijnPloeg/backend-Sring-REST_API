package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import nl.marijnploeg.kitereparatie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }

    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping("/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(appUserService.getUserByEmail(email));
    }
}
