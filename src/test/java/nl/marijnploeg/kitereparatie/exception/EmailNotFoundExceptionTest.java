package nl.marijnploeg.kitereparatie.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class EmailNotFoundExceptionTest {
    @Test
    public void testConstructor() {
        EmailNotFoundException actualEmailNotFoundException = new EmailNotFoundException("weird@email.com");
        assertNull(actualEmailNotFoundException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"),
                        ".kitereparatie.exception.EmailNotFoundException: Cannot find email weird@email.com"),
                actualEmailNotFoundException.toString());
        assertEquals(0, actualEmailNotFoundException.getSuppressed().length);
        assertEquals("Cannot find email weird@email.com", actualEmailNotFoundException.getMessage());
        assertEquals("Cannot find email weird@email.com", actualEmailNotFoundException.getLocalizedMessage());
    }
}

