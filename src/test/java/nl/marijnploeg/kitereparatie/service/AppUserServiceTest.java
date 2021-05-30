package nl.marijnploeg.kitereparatie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.marijnploeg.kitereparatie.exception.ApiExceptions.ApiRequestException;

import nl.marijnploeg.kitereparatie.exception.EmailNotFoundException;

import nl.marijnploeg.kitereparatie.model.Address;

import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserService.class, ConfirmationTokenService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
public class AppUserServiceTest {
    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @Test
    public void testLoadUserByUsername() throws UsernameNotFoundException {
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
        appUser.setProfileImg("myImg.jpeg");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Marijn");
        appUser.setAppUserId(123L);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(ofResult);
        assertSame(appUser, this.appUserService.loadUserByUsername("marijnploeg@gmail.com"));
        verify(this.appUserRepository).findByEmail(anyString());
    }

    @Test
    public void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(EmailNotFoundException.class, () -> this.appUserService.loadUserByUsername("marijnploeg@gmail.com"));
        verify(this.appUserRepository).findByEmail(anyString());
    }

    @Test
    public void testSignUpUser() {
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
        appUser.setProfileImg("myImg.jpeg");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Marijn");
        appUser.setAppUserId(123L);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(ofResult);
        assertThrows(ApiRequestException.class, () -> this.appUserService
                .signUpUser(new AppUser("Marijn", "Ploeg", "marijnploeg@gmail.com", "password", AppUserRole.CUSTOMER)));
        verify(this.appUserRepository).findByEmail(anyString());
    }

    @Test
    public void testSignUpUser2() {
        doNothing().when(this.confirmationTokenService)
                .safeConfirmationToken(any());
        when(this.bCryptPasswordEncoder.encode(any())).thenReturn("foo");

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
        appUser.setLastName("Doe");
        appUser.setEmail("jane.doe@example.org");
        appUser.setPassword("iloveyou");
        appUser.setProfileImg("Profile Img");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setAppUserId(123L);
        when(this.appUserRepository.save(any())).thenReturn(appUser);
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        AppUser appUser1 = new AppUser("Jane", "Doe", "jane.doe@example.org", "iloveyou", AppUserRole.CUSTOMER);
        this.appUserService.signUpUser(appUser1);
        verify(this.appUserRepository).save(any());
        verify(this.appUserRepository).findByEmail(anyString());
        verify(this.bCryptPasswordEncoder).encode(any());
        verify(this.confirmationTokenService)
                .safeConfirmationToken(any());
        assertEquals("foo", appUser1.getPassword());
    }

    @Test
    public void testSignUpUser3() {
        doNothing().when(this.confirmationTokenService)
                .safeConfirmationToken(any());
        when(this.bCryptPasswordEncoder.encode(any())).thenReturn("foo");

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
        when(this.appUserRepository.save(any())).thenReturn(appUser);
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        AppUser appUser1 = mock(AppUser.class);
        doNothing().when(appUser1).setPassword(anyString());
        when(appUser1.getPassword()).thenReturn("foo");
        when(appUser1.getUsername()).thenReturn("foo");
        this.appUserService.signUpUser(appUser1);
        verify(appUser1).setPassword(anyString());
        verify(appUser1).getPassword();
        verify(appUser1).getUsername();
        verify(this.appUserRepository).save(any());
        verify(this.appUserRepository).findByEmail(anyString());
        verify(this.bCryptPasswordEncoder).encode(any());
        verify(this.confirmationTokenService)
                .safeConfirmationToken(any());
    }

    @Test
    public void testEnableAppUser() {
        when(this.appUserRepository.enableAppUser(anyString())).thenReturn(1);
        assertEquals(1, this.appUserService.enableAppUser("marijnploeg@gmail.com"));
        verify(this.appUserRepository).enableAppUser(anyString());
    }

    @Test
    public void testGetAppUsers() {
        ArrayList<AppUser> appUserList = new ArrayList<AppUser>();
        when(this.appUserRepository.findAll()).thenReturn(appUserList);
        List<AppUser> actualAppUsers = this.appUserService.getAppUsers();
        assertSame(appUserList, actualAppUsers);
        assertTrue(actualAppUsers.isEmpty());
        verify(this.appUserRepository).findAll();
    }

    @Test
    public void testGetUserByEmail() {
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
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(ofResult);
        Optional<AppUser> actualUserByEmail = this.appUserService.getUserByEmail("marijnploeg@gmail.com");
        assertSame(ofResult, actualUserByEmail);
        assertTrue(actualUserByEmail.isPresent());
        verify(this.appUserRepository).findByEmail(anyString());
    }

    @Test
    public void testSave() {
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
        when(this.appUserRepository.save(any())).thenReturn(appUser);
        assertSame(appUser,
                this.appUserService.save(new AppUser("Marijn", "Ploeg", "marijnploeg@gmail.com", "password", AppUserRole.CUSTOMER)));
        verify(this.appUserRepository).save(any());
    }
}

