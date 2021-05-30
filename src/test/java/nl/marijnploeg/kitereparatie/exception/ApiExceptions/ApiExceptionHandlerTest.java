package nl.marijnploeg.kitereparatie.exception.ApiExceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiExceptionHandlerTest {
    @Test
    public void testHandleApiRequestException() {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        ResponseEntity<Object> actualHandleApiRequestExceptionResult = apiExceptionHandler
                .handleApiRequestException(new ApiRequestException("An error occurred"));
        assertTrue(actualHandleApiRequestExceptionResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleApiRequestExceptionResult.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST,
                ((ApiException) actualHandleApiRequestExceptionResult.getBody()).getHttpStatus());
        assertEquals("An error occurred", ((ApiException) actualHandleApiRequestExceptionResult.getBody()).getMessage());
    }

    @Test
    public void testHandleApiRequestException2() {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();

        ApiRequestException apiRequestException = new ApiRequestException("An error occurred");
        apiRequestException.addSuppressed(new Throwable());
        ResponseEntity<Object> actualHandleApiRequestExceptionResult = apiExceptionHandler
                .handleApiRequestException(apiRequestException);
        assertTrue(actualHandleApiRequestExceptionResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleApiRequestExceptionResult.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST,
                ((ApiException) actualHandleApiRequestExceptionResult.getBody()).getHttpStatus());
        assertEquals("An error occurred", ((ApiException) actualHandleApiRequestExceptionResult.getBody()).getMessage());
    }
}

