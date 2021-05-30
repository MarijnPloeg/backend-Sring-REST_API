package nl.marijnploeg.kitereparatie.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.marijnploeg.kitereparatie.exception.DatabaseErrorException;
import nl.marijnploeg.kitereparatie.exception.NotAuthorizedException;
import nl.marijnploeg.kitereparatie.exception.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ExceptionController.class})
@ExtendWith(SpringExtension.class)
public class ExceptionControllerTest {
    @Autowired
    private ExceptionController exceptionController;

    @Test
    public void testException() {
        ResponseEntity<Object> actualExceptionResult = this.exceptionController
                .exception(new DatabaseErrorException("An error occurred"));
        assertEquals("<404 NOT_FOUND Not Found,An error occurred,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
    }

    @Test
    public void testException2() {
        DatabaseErrorException databaseErrorException = new DatabaseErrorException("An error occurred");
        databaseErrorException.addSuppressed(new Throwable());
        ResponseEntity<Object> actualExceptionResult = this.exceptionController.exception(databaseErrorException);
        assertEquals("<404 NOT_FOUND Not Found,An error occurred,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
    }

    @Test
    public void testException3() {
        DatabaseErrorException databaseErrorException = mock(DatabaseErrorException.class);
        when(databaseErrorException.getMessage()).thenReturn("foo");
        ResponseEntity<Object> actualExceptionResult = this.exceptionController.exception(databaseErrorException);
        assertEquals("<404 NOT_FOUND Not Found,foo,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
        verify(databaseErrorException).getMessage();
    }

    @Test
    public void testException4() {
        ResponseEntity<Object> actualExceptionResult = this.exceptionController
                .exception(new NotAuthorizedException("An error occurred"));
        assertEquals("<401 UNAUTHORIZED Unauthorized,An error occurred,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.UNAUTHORIZED, actualExceptionResult.getStatusCode());
    }

    @Test
    public void testException5() {
        NotAuthorizedException notAuthorizedException = new NotAuthorizedException("An error occurred");
        notAuthorizedException.addSuppressed(new Throwable());
        ResponseEntity<Object> actualExceptionResult = this.exceptionController.exception(notAuthorizedException);
        assertEquals("<401 UNAUTHORIZED Unauthorized,An error occurred,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.UNAUTHORIZED, actualExceptionResult.getStatusCode());
    }

    @Test
    public void testException6() {
        NotAuthorizedException notAuthorizedException = mock(NotAuthorizedException.class);
        when(notAuthorizedException.getMessage()).thenReturn("foo");
        ResponseEntity<Object> actualExceptionResult = this.exceptionController.exception(notAuthorizedException);
        assertEquals("<401 UNAUTHORIZED Unauthorized,foo,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.UNAUTHORIZED, actualExceptionResult.getStatusCode());
        verify(notAuthorizedException).getMessage();
    }

    @Test
    public void testException7() {
        ResponseEntity<Object> actualExceptionResult = this.exceptionController
                .exception(new RecordNotFoundException("An error occurred"));
        assertEquals("<404 NOT_FOUND Not Found,An error occurred,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
    }

    @Test
    public void testException8() {
        RecordNotFoundException recordNotFoundException = new RecordNotFoundException("An error occurred");
        recordNotFoundException.addSuppressed(new Throwable());
        ResponseEntity<Object> actualExceptionResult = this.exceptionController.exception(recordNotFoundException);
        assertEquals("<404 NOT_FOUND Not Found,An error occurred,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
    }

    @Test
    public void testException9() {
        RecordNotFoundException recordNotFoundException = mock(RecordNotFoundException.class);
        when(recordNotFoundException.getMessage()).thenReturn("foo");
        ResponseEntity<Object> actualExceptionResult = this.exceptionController.exception(recordNotFoundException);
        assertEquals("<404 NOT_FOUND Not Found,foo,[]>", actualExceptionResult.toString());
        assertTrue(actualExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
        verify(recordNotFoundException).getMessage();
    }
}

