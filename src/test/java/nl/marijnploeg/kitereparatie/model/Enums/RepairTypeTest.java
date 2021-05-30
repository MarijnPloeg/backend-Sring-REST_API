package nl.marijnploeg.kitereparatie.model.Enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RepairTypeTest {
    @Test
    public void testFromType() {
        assertThrows(UnsupportedOperationException.class, () -> RepairType.fromType("Type"));
        assertEquals(RepairType.KITE, RepairType.fromType("Bar"));
        assertEquals(RepairType.KITE, RepairType.fromType("bar"));
        assertEquals(RepairType.BOARD, RepairType.fromType("Board"));
        assertEquals(RepairType.WETSUIT, RepairType.fromType("Wetsuit"));
        assertEquals(RepairType.WETSUIT, RepairType.fromType("wetsuit"));
        assertEquals(RepairType.KITE, RepairType.fromType("Kite"));
        assertEquals(RepairType.KITE, RepairType.fromType("kite"));
        assertEquals(RepairType.BOARD, RepairType.fromType("board"));
    }

    @Test
    public void testGetType() {
        assertEquals("Kite", RepairType.KITE.getType());
    }
}

