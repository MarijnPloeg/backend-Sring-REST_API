package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
