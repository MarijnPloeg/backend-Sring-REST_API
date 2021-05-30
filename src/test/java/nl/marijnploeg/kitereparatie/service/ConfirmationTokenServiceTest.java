package nl.marijnploeg.kitereparatie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import nl.marijnploeg.kitereparatie.model.Address;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.model.ConfirmationToken;
import nl.marijnploeg.kitereparatie.repository.ConfirmationTokenRepository;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ConfirmationTokenService.class})
@ExtendWith(SpringExtension.class)
public class ConfirmationTokenServiceTest {
    @MockBean
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Test
    public void testSafeConfirmationToken() {
        Address address = new Address();
        address.setPostalCode("Postal Code");
        address.setStreetName("Street Name");
        address.setAddressID(1L);
        address.setCountry("Country");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Oxford");
        address.setHouseNumber(10);
        address.setState("MD");

        AppUser appUser = new AppUser();
        appUser.setLastName("Ploeg");
        appUser.setEmail("marijnploeg@gmail.com");
        appUser.setPassword("password");
        appUser.setProfileImg("myImage.jpeg");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Marijn");
        appUser.setAppUserId(123L);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setAppUser(appUser);
        confirmationToken.setId(123L);
        confirmationToken.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setToken("ABC123");
        when(this.confirmationTokenRepository.save(any())).thenReturn(confirmationToken);
        this.confirmationTokenService.safeConfirmationToken(new ConfirmationToken());
        verify(this.confirmationTokenRepository).save(any());
    }

    @Test
    public void testGetToken() {
        Address address = new Address();
        address.setPostalCode("2011 RT");
        address.setStreetName("Jansstraat");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");

        AppUser appUser = new AppUser();
        appUser.setLastName("Ploeg");
        appUser.setEmail("marijnploeg@gmail.com");
        appUser.setPassword("password");
        appUser.setProfileImg("myImage.jpeg");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Marijn");
        appUser.setAppUserId(123L);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setAppUser(appUser);
        confirmationToken.setId(123L);
        confirmationToken.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setToken("ABC123");
        Optional<ConfirmationToken> ofResult = Optional.of(confirmationToken);
        when(this.confirmationTokenRepository.findByToken(anyString())).thenReturn(ofResult);
        Optional<ConfirmationToken> actualToken = this.confirmationTokenService.getToken("ABC123");
        assertSame(ofResult, actualToken);
        assertTrue(actualToken.isPresent());
        verify(this.confirmationTokenRepository).findByToken(anyString());
    }

    @Test
    public void testSetConfirmedAt() {
        when(this.confirmationTokenRepository.updateConfirmedAt(anyString(), any()))
                .thenReturn(1);
        assertEquals(1, this.confirmationTokenService.setConfirmedAt("ABC123"));
        verify(this.confirmationTokenRepository).updateConfirmedAt(anyString(), any());
    }
}

