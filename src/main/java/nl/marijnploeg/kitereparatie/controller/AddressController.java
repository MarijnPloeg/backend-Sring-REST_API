package nl.marijnploeg.kitereparatie.controller;

import javassist.NotFoundException;
import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import nl.marijnploeg.kitereparatie.service.AddressService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @CrossOrigin
    @GetMapping(value = "")
    public ResponseEntity<Object> getAddresses() {
        return ResponseEntity.ok().body(addressService.getAddresses());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAddressById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(addressService.getAddressById(id));
    }

    @CrossOrigin
    @GetMapping(value = "/klant/{id}")
    public ResponseEntity<Object> getAddressByUserId(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(addressService.getAddressByUserId(id));
    }


    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAddressById(@PathVariable("id") long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }



//    @CrossOrigin
//    @PostMapping(value = "")
//    public ResponseEntity<Object> saveAddress(@RequestBody AppUser appUser, @RequestBody Address address) {
//        long newId = addressService.saveAddress(appUser, address);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newId).toUri();
//
//        return ResponseEntity.created(location).body(location);
//    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
        addressService.updateAddress(id, address);
        return ResponseEntity.noContent().build();
    }
}

