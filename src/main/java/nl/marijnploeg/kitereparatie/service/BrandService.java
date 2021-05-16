package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.exception.RecordNotFoundException;
import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
import nl.marijnploeg.kitereparatie.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();}

    public List<Brand> getBrandByName(String brandName) {
        return brandRepository.findByBrandName(brandName);
    }

    public List<Brand> getBrandsByType(BrandType brandType) {
        return brandRepository.findByBrandType(brandType);
    }

    public long addBrand(Brand brand) {
        Brand newBrand = brandRepository.save(brand);
        return newBrand.getBrandID();
    }

    public void deleteBrandById(long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
