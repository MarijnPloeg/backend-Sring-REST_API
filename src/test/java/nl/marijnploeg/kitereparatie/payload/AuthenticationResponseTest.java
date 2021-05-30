package nl.marijnploeg.kitereparatie.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationResponseTest {
    @Test
    public void testConstructor() {
        assertEquals("Jwt", (new AuthenticationResponse("Jwt")).getJwt());
    }
}

