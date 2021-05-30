package nl.marijnploeg.kitereparatie.security.rolesAndPermissions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppUserPermissionTest {
    @Test
    public void testConstructor() {
        assertEquals("Permission", (new AppUserPermission("Permission")).getPermission());
    }
}

