package nl.marijnploeg.kitereparatie.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationRequestTest {
    @Test
    public void testConstructor() {
        AuthenticationRequest actualAuthenticationRequest = new AuthenticationRequest();
        actualAuthenticationRequest.setEmail("hans@lieben.nl");
        actualAuthenticationRequest.setPassword("password1");
        assertEquals("hans@lieben.nl", actualAuthenticationRequest.getEmail());
        assertEquals("password1", actualAuthenticationRequest.getPassword());
    }

    @Test
    public void testConstructor2() {
        AuthenticationRequest actualAuthenticationRequest = new AuthenticationRequest("marijnploeg@gmail.com", "password");
        actualAuthenticationRequest.setEmail("marijnploeg@gmail.com");
        actualAuthenticationRequest.setPassword("password");
        assertEquals("marijnploeg@gmail.com", actualAuthenticationRequest.getEmail());
        assertEquals("password", actualAuthenticationRequest.getPassword());
    }
}

