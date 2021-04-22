package nl.marijnploeg.kitereparatie.service;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;

import java.util.List;

public interface BrandService {
    List<Brand> getBrands();
    List<Brand> getBrandsByType(BrandType brandType);
    Brand getBrandByName(String brandName);
    void deleteBrand(String brandName);
    long addBrand(Brand brand);
}
