package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
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

    @CrossOrigin
    @GetMapping(value = "")
    public ResponseEntity<Object> getBrands() {
        return ResponseEntity.ok().body(brandService.getAllBrands());
    }

    @CrossOrigin
    @GetMapping(value = "/brand/{brandname}")
    public ResponseEntity<Object> getBrandByName(@PathVariable("brandname") String brandName) {
        return ResponseEntity.ok().body(brandService.getBrandByName(brandName));
    }

    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<Object> addBrand(@RequestBody Brand brand) {
        long newId = brandService.addBrand(brand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id{")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("Nieuw merk op link: ");
    }

    @CrossOrigin
    @GetMapping(value = "/type/{brandtype}")
    public ResponseEntity<Object> getBrandsByType(@PathVariable("brandtype") BrandType brandType) {
        return ResponseEntity.ok().body(brandService.getBrandsByType(brandType));
    }

    @CrossOrigin
    @DeleteMapping(value = "/brand/{brandId}")
    public ResponseEntity<Object> deleteByName(@PathVariable("brandId") long id) {
        brandService.deleteBrandById(id);
        return ResponseEntity.noContent().build();
    }
}