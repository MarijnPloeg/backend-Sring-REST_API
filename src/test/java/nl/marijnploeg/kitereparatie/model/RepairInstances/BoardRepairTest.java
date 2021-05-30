package nl.marijnploeg.kitereparatie.model.RepairInstances;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.marijnploeg.kitereparatie.model.Enums.BoardType;
import org.junit.jupiter.api.Test;

public class BoardRepairTest {
    @Test
    public void testConstructor() {
        BoardRepair actualBoardRepair = new BoardRepair();
        actualBoardRepair.setBoardType(BoardType.TWINTIP);
        assertEquals(BoardType.TWINTIP, actualBoardRepair.getBoardType());
    }

    @Test
    public void testConstructor2() {
        BoardRepair actualBoardRepair = new BoardRepair(BoardType.TWINTIP);
        actualBoardRepair.setBoardType(BoardType.TWINTIP);
        assertEquals(BoardType.TWINTIP, actualBoardRepair.getBoardType());
    }
}

