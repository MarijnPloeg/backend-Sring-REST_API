package nl.marijnploeg.kitereparatie.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class NotAuthorizedExceptionTest {
    @Test
    public void testConstructor() {
        NotAuthorizedException actualNotAuthorizedException = new NotAuthorizedException();
        assertNull(actualNotAuthorizedException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"), ".kitereparatie.exception.NotAuthorizedException"),
                actualNotAuthorizedException.toString());
        assertEquals(0, actualNotAuthorizedException.getSuppressed().length);
        assertNull(actualNotAuthorizedException.getMessage());
        assertNull(actualNotAuthorizedException.getLocalizedMessage());
    }

    @Test
    public void testConstructor2() {
        NotAuthorizedException actualNotAuthorizedException = new NotAuthorizedException("An error occurred");
        assertNull(actualNotAuthorizedException.getCause());
        assertEquals(
                String.join("", "nl.", System.getProperty("user.name"),
                        ".kitereparatie.exception.NotAuthorizedException: An error occurred"),
                actualNotAuthorizedException.toString());
        assertEquals(0, actualNotAuthorizedException.getSuppressed().length);
        assertEquals("An error occurred", actualNotAuthorizedException.getMessage());
        assertEquals("An error occurred", actualNotAuthorizedException.getLocalizedMessage());
    }
}

