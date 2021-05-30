package nl.marijnploeg.kitereparatie.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.marijnploeg.kitereparatie.model.Enums.RepairType;
import org.junit.jupiter.api.Test;

public class RepairTypeConverterTest {
    @Test
    public void testConvertToDatabaseColumn() {
        assertEquals("Kite", (new RepairTypeConverter()).convertToDatabaseColumn(RepairType.KITE));
    }

    @Test
    public void testConvertToEntityAttribute() {
        // TODO: Write assertions for this test

        (new RepairTypeConverter()).convertToEntityAttribute("42");
    }

    @Test
    public void testConvertToEntityAttribute2() {
        assertEquals(RepairType.KITE, (new RepairTypeConverter()).convertToEntityAttribute("Kite"));
    }
}

