package nl.marijnploeg.kitereparatie.model.Authority;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthorityTest {
    @Test
    public void testConstructor() {
        Authority actualAuthority = new Authority();
        actualAuthority.setAuthority("HansLieben");
        actualAuthority.setEmail("hans@lieben.nl");
        assertEquals("HansLieben", actualAuthority.getAuthority());
        assertEquals("hans@lieben.nl", actualAuthority.getEmail());
    }

    @Test
    public void testConstructor2() {
        Authority actualAuthority = new Authority("marijnploeg@gmail.com", "MarijnPloeg");
        actualAuthority.setAuthority("MarijnPloeg");
        actualAuthority.setEmail("marijnploeg@gmail.com");
        assertEquals("MarijnPloeg", actualAuthority.getAuthority());
        assertEquals("marijnploeg@gmail.com", actualAuthority.getEmail());
    }
}

