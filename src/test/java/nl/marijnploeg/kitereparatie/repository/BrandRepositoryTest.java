package nl.marijnploeg.kitereparatie.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {BrandRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testFindByBrandName() {
        Brand brand = new Brand();
        brand.setBrandID(1L);
        brand.setBrandType(BrandType.KITE);
        brand.setBrandName("Brand Name");

        Brand brand1 = new Brand();
        brand1.setBrandID(1L);
        brand1.setBrandType(BrandType.KITE);
        brand1.setBrandName("Brand Name");
        this.brandRepository.<Brand>save(brand);
        this.brandRepository.<Brand>save(brand1);
        assertTrue(this.brandRepository.findByBrandName("foo").isEmpty());
    }

    @Test
    public void testFindByBrandType() {
        Brand brand = new Brand();
        brand.setBrandID(1L);
        brand.setBrandType(BrandType.KITE);
        brand.setBrandName("Brand Name");

        Brand brand1 = new Brand();
        brand1.setBrandID(1L);
        brand1.setBrandType(BrandType.KITE);
        brand1.setBrandName("Brand Name");
        this.brandRepository.<Brand>save(brand);
        this.brandRepository.<Brand>save(brand1);
        assertTrue(this.brandRepository.findByBrandType(BrandType.KITE).isEmpty());
    }
}

