package nl.marijnploeg.kitereparatie.model.Enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeliveryOptionTest {
    @Test
    public void testValueOf() {
        // TODO: Write assertions for this class

        DeliveryOption.valueOf("Name");
    }

    @Test
    public void testValueOf2() {
        // TODO: Write assertions for this class

        DeliveryOption.valueOf("foo");
    }

    @Test
    public void testValueOf3() {
        assertEquals(DeliveryOption.BYPOST, DeliveryOption.valueOf("BYPOST"));
    }

    @Test
    public void testValues() {
        DeliveryOption[] actualValuesResult = DeliveryOption.values();
        assertEquals(2, actualValuesResult.length);
        assertEquals(DeliveryOption.BYPOST, actualValuesResult[0]);
        assertEquals(DeliveryOption.INSTORE, actualValuesResult[1]);
    }
}

