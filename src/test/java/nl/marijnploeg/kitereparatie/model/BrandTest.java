package nl.marijnploeg.kitereparatie.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
import org.junit.jupiter.api.Test;

public class BrandTest {
    @Test
    public void testConstructor() {
        Brand actualBrand = new Brand();
        actualBrand.setBrandID(1L);
        actualBrand.setBrandName("Naish");
        actualBrand.setBrandType(BrandType.KITE);
        assertEquals(1L, actualBrand.getBrandID());
        assertEquals("Naish", actualBrand.getBrandName());
        assertEquals(BrandType.KITE, actualBrand.getBrandType());
    }

    @Test
    public void testConstructor2() {
        Brand actualBrand = new Brand(1L, "North", BrandType.KITE);
        assertEquals(1L, actualBrand.getBrandID());
        assertEquals(BrandType.KITE, actualBrand.getBrandType());
        assertEquals("North", actualBrand.getBrandName());
    }
}

