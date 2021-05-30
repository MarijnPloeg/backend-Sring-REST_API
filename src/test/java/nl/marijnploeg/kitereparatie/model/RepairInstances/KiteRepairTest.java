package nl.marijnploeg.kitereparatie.model.RepairInstances;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KiteRepairTest {
    @Test
    public void testConstructor() {
        KiteRepair actualKiteRepair = new KiteRepair();
        actualKiteRepair.setCanopy(true);
        actualKiteRepair.setLeadingEdge(true);
        actualKiteRepair.setLeakage(true);
        actualKiteRepair.setLocation("Trailing edge");
        actualKiteRepair.setStrut(true);
        assertEquals("Trailing edge", actualKiteRepair.getLocation());
        assertTrue(actualKiteRepair.isCanopy());
        assertTrue(actualKiteRepair.isLeadingEdge());
        assertTrue(actualKiteRepair.isLeakage());
        assertTrue(actualKiteRepair.isStrut());
    }

    @Test
    public void testConstructor2() {
        KiteRepair actualKiteRepair = new KiteRepair(true, true, true, true, "Location");
        actualKiteRepair.setCanopy(true);
        actualKiteRepair.setLeadingEdge(true);
        actualKiteRepair.setLeakage(true);
        actualKiteRepair.setLocation("Strut");
        actualKiteRepair.setStrut(true);
        assertEquals("Strut", actualKiteRepair.getLocation());
        assertTrue(actualKiteRepair.isCanopy());
        assertTrue(actualKiteRepair.isLeadingEdge());
        assertTrue(actualKiteRepair.isLeakage());
        assertTrue(actualKiteRepair.isStrut());
    }
}

