package nl.marijnploeg.kitereparatie.exception.ApiExceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ApiExceptionTest {
    @Test
    public void testConstructor() {
        ApiException actualApiException = new ApiException("An error occurred", HttpStatus.CONTINUE, null);
        assertEquals(HttpStatus.CONTINUE, actualApiException.getHttpStatus());
        assertEquals("An error occurred", actualApiException.getMessage());
        assertNull(actualApiException.getTimestamp());
    }
}

