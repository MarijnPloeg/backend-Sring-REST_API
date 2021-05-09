package nl.marijnploeg.kitereparatie.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
import org.junit.jupiter.api.Test;

public class BrandTest {
    @Test
    public void testConstructor() {
        Brand actualBrand = new Brand(1L, "Brand Name", BrandType.KITE);
        assertEquals(1L, actualBrand.getBrandID());
        assertEquals(BrandType.KITE, actualBrand.getBrandType());
        assertEquals("Brand Name", actualBrand.getBrandName());
    }

    @Test
    public void testSetBrandID() {
        Brand brand = new Brand(1L, "Brand Name", BrandType.KITE);
        brand.setBrandID(1L);
        assertEquals(1L, brand.getBrandID());
    }

    @Test
    public void testSetBrandName() {
        Brand brand = new Brand(1L, "Brand Name", BrandType.KITE);
        brand.setBrandName("Brand Name");
        assertEquals("Brand Name", brand.getBrandName());
    }

    @Test
    public void testSetBrandType() {
        Brand brand = new Brand(1L, "Brand Name", BrandType.KITE);
        brand.setBrandType(BrandType.KITE);
        assertEquals(BrandType.KITE, brand.getBrandType());
    }
}

