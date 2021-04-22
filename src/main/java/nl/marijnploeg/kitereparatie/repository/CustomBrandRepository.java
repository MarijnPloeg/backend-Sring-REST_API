package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;

import java.util.Collection;
import java.util.List;

public interface CustomBrandRepository {
    public Brand getBrandByName(String brandName);
    //    Creating exists by methods so we can only delete brands if they exist (Check service)
    public boolean existsByName(String brandName);
    public void deleteBrand(String brandName);
    //    Creating exists by methods so we get brands by type if type exists (Check service)
    public boolean existsByType(BrandType brandType);
    public List<Brand> getBrandsByType(BrandType brandType);
}
