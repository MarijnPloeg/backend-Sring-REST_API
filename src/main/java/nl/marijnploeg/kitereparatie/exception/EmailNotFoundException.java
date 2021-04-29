package nl.marijnploeg.kitereparatie.exception;

public class EmailNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailNotFoundException(String email) {
        super("Cannot find email " + email);
    }
}
