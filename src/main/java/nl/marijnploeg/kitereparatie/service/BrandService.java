package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;
import nl.marijnploeg.kitereparatie.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getBrandByName(String brandName) {
        return brandRepository.findByBrandName(brandName);
    }

    public List<Brand> getBrandsByType(BrandType brandType) {
        return brandRepository.findByBrandType(brandType);
    }

}
