package nl.marijnploeg.kitereparatie.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class RecordNotFoundExceptionTest {
    @Test
    public void testConstructor() {
        RecordNotFoundException actualRecordNotFoundException = new RecordNotFoundException();
        assertNull(actualRecordNotFoundException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"), ".kitereparatie.exception.RecordNotFoundException"),
                actualRecordNotFoundException.toString());
        assertEquals(0, actualRecordNotFoundException.getSuppressed().length);
        assertNull(actualRecordNotFoundException.getMessage());
        assertNull(actualRecordNotFoundException.getLocalizedMessage());
    }

    @Test
    public void testConstructor2() {
        RecordNotFoundException actualRecordNotFoundException = new RecordNotFoundException("An error occurred");
        assertNull(actualRecordNotFoundException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"),
                        ".kitereparatie.exception.RecordNotFoundException: An error occurred"),
                actualRecordNotFoundException.toString());
        assertEquals(0, actualRecordNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualRecordNotFoundException.getMessage());
        assertEquals("An error occurred", actualRecordNotFoundException.getLocalizedMessage());
    }
}

