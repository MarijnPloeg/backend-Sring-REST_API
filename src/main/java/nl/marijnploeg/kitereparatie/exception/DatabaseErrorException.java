package nl.marijnploeg.kitereparatie.exception;

public class DatabaseErrorException extends RuntimeException {
    public DatabaseErrorException() {super();}
    public DatabaseErrorException(String message) {super(message);}
}
