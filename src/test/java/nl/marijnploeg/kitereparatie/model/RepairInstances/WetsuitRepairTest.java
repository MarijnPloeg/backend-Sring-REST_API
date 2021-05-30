package nl.marijnploeg.kitereparatie.model.RepairInstances;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WetsuitRepairTest {
    @Test
    public void testConstructor() {
        WetsuitRepair actualWetsuitRepair = new WetsuitRepair();
        actualWetsuitRepair.setDamageSizeCM(3);
        actualWetsuitRepair.setZipper(true);
        assertEquals(3, actualWetsuitRepair.getDamageSizeCM());
        assertTrue(actualWetsuitRepair.isZipper());
    }

    @Test
    public void testConstructor2() {
        WetsuitRepair actualWetsuitRepair = new WetsuitRepair(true, 3);
        actualWetsuitRepair.setDamageSizeCM(3);
        actualWetsuitRepair.setZipper(true);
        assertEquals(3, actualWetsuitRepair.getDamageSizeCM());
        assertTrue(actualWetsuitRepair.isZipper());
    }
}

