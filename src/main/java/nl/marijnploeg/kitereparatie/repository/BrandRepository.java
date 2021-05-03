package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Brand;

import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByBrandName(String brandName);

    List<Brand> findByBrandType(BrandType brandType);
}
