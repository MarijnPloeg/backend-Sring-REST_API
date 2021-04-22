package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BrandRepository extends JpaRepository<Brand, Long>, CustomBrandRepository {

}
