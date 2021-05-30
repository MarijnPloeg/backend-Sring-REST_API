package nl.marijnploeg.kitereparatie.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.Enums.BrandType;
import nl.marijnploeg.kitereparatie.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BrandService.class})
@ExtendWith(SpringExtension.class)
public class BrandServiceTest {
    @MockBean
    private BrandRepository brandRepository;

    @Autowired
    private BrandService brandService;

    @Test
    public void testGetBrandByName() {
        ArrayList<Brand> brandList = new ArrayList<Brand>();
        when(this.brandRepository.findByBrandName(anyString())).thenReturn(brandList);
        List<Brand> actualBrandByName = this.brandService.getBrandByName("North");
        assertSame(brandList, actualBrandByName);
        assertTrue(actualBrandByName.isEmpty());
        verify(this.brandRepository).findByBrandName(anyString());
    }

    @Test
    public void testGetBrandsByType() {
        ArrayList<Brand> brandList = new ArrayList<Brand>();
        when(this.brandRepository.findByBrandType(any())).thenReturn(brandList);
        List<Brand> actualBrandsByType = this.brandService.getBrandsByType(BrandType.KITE);
        assertSame(brandList, actualBrandsByType);
        assertTrue(actualBrandsByType.isEmpty());
        verify(this.brandRepository).findByBrandType(any());
    }
}

