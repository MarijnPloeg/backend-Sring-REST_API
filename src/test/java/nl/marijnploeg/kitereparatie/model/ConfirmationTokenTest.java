package nl.marijnploeg.kitereparatie.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;

import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.junit.jupiter.api.Test;

public class ConfirmationTokenTest {
    @Test
    public void testConstructor() {
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime ofResult1 = LocalDateTime.of(1, 1, 1, 1, 1);
        AppUser appUser = new AppUser("Marijn", "Ploeg", "marijnploeg@gmail.com", "password", AppUserRole.CUSTOMER);
        ConfirmationToken actualConfirmationToken = new ConfirmationToken("agbharbfgyuabvfvaeugfuya", ofResult, ofResult1, appUser);
        assertSame(appUser, actualConfirmationToken.getAppUser());
        assertEquals("agbharbfgyuabvfvaeugfuya", actualConfirmationToken.getToken());
        assertSame(ofResult1, actualConfirmationToken.getExpiresAt());
        assertSame(ofResult, actualConfirmationToken.getCreatedAt());
    }
}

