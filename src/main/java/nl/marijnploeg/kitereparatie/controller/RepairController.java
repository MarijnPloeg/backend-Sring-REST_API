package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Repair;
import nl.marijnploeg.kitereparatie.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @CrossOrigin
    @GetMapping(value = "")
    public ResponseEntity<Object> getRepairs() {
        return ResponseEntity.ok().body(repairService.getRepairs());
    }

    @CrossOrigin
    @PostMapping(value = "")
    public ResponseEntity<Object> createRepair(@RequestBody Repair repair) {
        long newId = repairService.saveRepair(repair);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("New repair created at: ");
    }

}
