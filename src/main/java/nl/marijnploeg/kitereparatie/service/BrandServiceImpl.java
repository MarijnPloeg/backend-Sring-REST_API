package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;
import nl.marijnploeg.kitereparatie.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrandsByType(BrandType brandType) {
        return brandRepository.getBrandsByType(brandType);
    }

    @Override
    public Brand getBrandByName(String brandName) {
        return brandRepository.getBrandByName(brandName);
    }

    @Override
    public void deleteBrand(String brandName) {
        brandRepository.deleteBrand(brandName);
    }

    @Override
    public long addBrand(Brand brand) {
        Brand newBrand = brandRepository.save(brand);
        return newBrand.getBrandID();
    }


}
