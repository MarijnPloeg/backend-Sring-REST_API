package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;
import nl.marijnploeg.kitereparatie.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getBrands() {
        return ResponseEntity.ok().body(brandService.getBrands());
    }

    @GetMapping(value = "/{brandName}")
    public ResponseEntity<Object> getBrandByName(@PathVariable("brandName") String brandName) {
        return ResponseEntity.ok().body(brandService.getBrandByName(brandName));
    }

    @GetMapping(value = "/{brandType}")
    public ResponseEntity<Object> getBrandByType(@PathVariable("brandType") BrandType brandType) {
        return ResponseEntity.ok().body(brandService.getBrandsByType(brandType));
    }

    @DeleteMapping(value = "/{brandName}")
    public ResponseEntity<Object> deleteBrand(@PathVariable("brandName") String brandName) {
        brandService.deleteBrand(brandName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addBrand(@RequestBody Brand brand) {
        long newId = brandService.addBrand(brand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

}
