package nl.marijnploeg.kitereparatie.model.RepairInstances;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BarRepairTest {
    @Test
    public void testConstructor() {
        BarRepair actualBarRepair = new BarRepair();
        actualBarRepair.setLengthCheck(true);
        assertTrue(actualBarRepair.isLengthCheck());
    }

    @Test
    public void testConstructor2() {
        BarRepair actualBarRepair = new BarRepair(true);
        actualBarRepair.setLengthCheck(true);
        assertTrue(actualBarRepair.isLengthCheck());
    }
}

