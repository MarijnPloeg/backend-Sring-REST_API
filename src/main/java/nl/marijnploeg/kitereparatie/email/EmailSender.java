package nl.marijnploeg.kitereparatie.email;

public interface EmailSender {
    void send(String to, String email);
}
