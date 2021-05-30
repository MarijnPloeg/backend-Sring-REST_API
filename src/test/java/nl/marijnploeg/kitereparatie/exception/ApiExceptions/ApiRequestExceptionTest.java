package nl.marijnploeg.kitereparatie.exception.ApiExceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class ApiRequestExceptionTest {
    @Test
    public void testConstructor() {
        ApiRequestException actualApiRequestException = new ApiRequestException("An error occurred");
        assertNull(actualApiRequestException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"),
                        ".kitereparatie.exception.ApiExceptions.ApiRequestException: An error occurred"),
                actualApiRequestException.toString());
        assertEquals(0, actualApiRequestException.getSuppressed().length);
        assertEquals("An error occurred", actualApiRequestException.getMessage());
        assertEquals("An error occurred", actualApiRequestException.getLocalizedMessage());
    }

    @Test
    public void testConstructor2() {
        Throwable throwable = new Throwable();
        ApiRequestException actualApiRequestException = new ApiRequestException("An error occurred", throwable);
        Throwable cause = actualApiRequestException.getCause();
        assertSame(throwable, cause);
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"),
                        ".kitereparatie.exception.ApiExceptions.ApiRequestException: An error occurred"),
                actualApiRequestException.toString());
        assertEquals("An error occurred", actualApiRequestException.getLocalizedMessage());
        Throwable[] suppressed = actualApiRequestException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertEquals("An error occurred", actualApiRequestException.getMessage());
        assertNull(cause.getLocalizedMessage());
        assertNull(cause.getCause());
        assertEquals("java.lang.Throwable", cause.toString());
        assertNull(cause.getMessage());
        assertSame(suppressed, cause.getSuppressed());
        assertSame(cause, throwable);
    }
}

