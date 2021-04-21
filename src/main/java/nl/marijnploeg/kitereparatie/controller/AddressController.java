package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping(value = "")
    public ResponseEntity<List<Address>> getAddresses() {
        return ResponseEntity.ok().body((addressRepository.findAll()));
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Object> getAddress(@PathVariable("id") long id) {
//        if (AddressRepository.existsById(id)) {
//            return ResponseEntity.ok().body(AddressRepository.existsById(id));
//        }
//    }
}
