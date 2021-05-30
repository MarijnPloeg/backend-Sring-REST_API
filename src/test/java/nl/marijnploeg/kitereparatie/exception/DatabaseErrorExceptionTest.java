package nl.marijnploeg.kitereparatie.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class DatabaseErrorExceptionTest {
    @Test
    public void testConstructor() {
        DatabaseErrorException actualDatabaseErrorException = new DatabaseErrorException();
        assertNull(actualDatabaseErrorException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"), ".kitereparatie.exception.DatabaseErrorException"),
                actualDatabaseErrorException.toString());
        assertEquals(0, actualDatabaseErrorException.getSuppressed().length);
        assertNull(actualDatabaseErrorException.getMessage());
        assertNull(actualDatabaseErrorException.getLocalizedMessage());
    }

    @Test
    public void testConstructor2() {
        DatabaseErrorException actualDatabaseErrorException = new DatabaseErrorException("An error occurred");
        assertNull(actualDatabaseErrorException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"),
                        ".kitereparatie.exception.DatabaseErrorException: An error occurred"),
                actualDatabaseErrorException.toString());
        assertEquals(0, actualDatabaseErrorException.getSuppressed().length);
        assertEquals("An error occurred", actualDatabaseErrorException.getMessage());
        assertEquals("An error occurred", actualDatabaseErrorException.getLocalizedMessage());
    }
}

