package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;
import nl.marijnploeg.kitereparatie.repository.Brand.BrandRepository;
import nl.marijnploeg.kitereparatie.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired
    private BrandRepository brandService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getBrands() {
        return ResponseEntity.ok().body(brandService.findAll());
    }

    @GetMapping(value = "/brand/{brandname}")
    public ResponseEntity<Object> getBrandByName(@PathVariable("brandname") String brandName) {
        return ResponseEntity.ok().body(brandService.findByBrandName(brandName));
    }

    @GetMapping(value = "/type/{brandtype}")
    public ResponseEntity<Object> getBrandsByType(@PathVariable("brandtype") BrandType brandType) {
        return ResponseEntity.ok().body(brandService.findByBrandType(brandType));
    }

    @DeleteMapping(value = "/brand/{brandId}")
    public ResponseEntity<Object> deleteByName(@PathVariable("brandId") long id) {
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}