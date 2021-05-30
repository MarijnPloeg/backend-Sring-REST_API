package nl.marijnploeg.kitereparatie.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import nl.marijnploeg.kitereparatie.model.Address;

import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AppUserController.class})
@ExtendWith(SpringExtension.class)
public class AppUserControllerTest {
    @Autowired
    private AppUserController appUserController;

    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private AppUserService appUserService;

    @Test
    public void testDownloadUserProfileImage() throws Exception {
        when(this.appUserService.downloadUserProfileImage((UUID) any())).thenReturn("AAAAAAAA".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userProfileId}/image/download",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/octet-stream"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("AAAAAAAA")));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(this.appUserService.getAppUsers()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetAppUserAddress() throws Exception {
        Address address = new Address();
        address.setPostalCode("Postal Code");
        address.setStreetName("Street Name");
        address.setAddressID(1L);
        address.setCountry("Country");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Haarlem");
        address.setHouseNumber(10);
        address.setState("Noord Holland");

        AppUser appUser = new AppUser();
        appUser.setLastName("Jan");
        appUser.setEmail("jan@zoon.nl");
        appUser.setPassword("password");
        appUser.setProfileImg("janImg.jpg");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Jan");
        appUser.setAppUserId(123L);
        Optional<AppUser> ofResult = Optional.<AppUser>of(appUser);

        Address address1 = new Address();
        address1.setPostalCode("Postal Code");
        address1.setStreetName("Street Name");
        address1.setAddressID(1L);
        address1.setCountry("Country");
        address1.setAppUsers(new ArrayList<AppUser>());
        address1.setCity("Amsterdam");
        address1.setHouseNumber(10);
        address1.setState("Noord Holland");

        AppUser appUser1 = new AppUser();
        appUser1.setLastName("Ploeg");
        appUser1.setEmail("pieter@ploeg.org");
        appUser1.setPassword("password1");
        appUser1.setProfileImg("myImg.png");
        appUser1.setLocked(true);
        appUser1.setAddress(address1);
        appUser1.setAppUserRole(AppUserRole.CUSTOMER);
        appUser1.setEnabled(true);
        appUser1.setFirstName("Pieter");
        appUser1.setAppUserId(123L);
        when(this.appUserRepository.save((AppUser) any())).thenReturn(appUser1);
        when(this.appUserRepository.findById((Long) any())).thenReturn(ofResult);

        Address address2 = new Address();
        address2.setPostalCode("Postal Code");
        address2.setStreetName("Street Name");
        address2.setAddressID(1L);
        address2.setCountry("Country");
        address2.setAppUsers(new ArrayList<AppUser>());
        address2.setCity("Den Haag");
        address2.setHouseNumber(10);
        address2.setState("Zuid Holland");
        String content = (new ObjectMapper()).writeValueAsString(address2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/{appUserId}/address", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"appUserId\":123,\"firstName\":\"Pieter\",\"lastName\":\"Ploeg\",\"email\":\"pieter@ploeg.org\",\"password\":\"password1"
                                        + "\",\"profileImg\":\"myImg.png\",\"address\":{\"addressID\":1,\"appUsers\":[],\"streetName\":\"Street Name\","
                                        + "\"houseNumber\":10,\"city\":\"Den haag\",\"postalCode\":\"Postal Code\",\"country\":\"Country\",\"state\":\"MD\"},\"appUserRole"
                                        + "\":\"CUSTOMER\",\"locked\":true,\"enabled\":true,\"username\":\"pieter@ploeg.org\",\"accountNonLocked\":false"
                                        + ",\"userImageLink\":\"myImg.png\",\"authorities\":[{\"authority\":\"CUSTOMER\"}],\"accountNonExpired\":true,"
                                        + "\"credentialsNonExpired\":true}")));
    }

    @Test
    public void testGetAppUserByEmail() throws Exception {
        Address address = new Address();
        address.setPostalCode("Postal Code");
        address.setStreetName("Street Name");
        address.setAddressID(1L);
        address.setCountry("Country");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Groningen");
        address.setHouseNumber(10);
        address.setState("Groningen");

        AppUser appUser = new AppUser();
        appUser.setLastName("Vaak");
        appUser.setEmail("klaasvaak@gmail.com");
        appUser.setPassword("prentenboek");
        appUser.setProfileImg("niceImg.jpg");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Klaas");
        appUser.setAppUserId(123L);
        Optional<AppUser> ofResult = Optional.<AppUser>of(appUser);
        when(this.appUserService.getUserByEmail(anyString())).thenReturn(ofResult);

        Address address1 = new Address();
        address1.setPostalCode("Postal Code");
        address1.setStreetName("Street Name");
        address1.setAddressID(1L);
        address1.setCountry("Nederland");
        address1.setAppUsers(new ArrayList<AppUser>());
        address1.setCity("Groningen");
        address1.setHouseNumber(10);
        address1.setState("Groningen");

        AppUser appUser1 = new AppUser();
        appUser1.setLastName("Vaak");
        appUser1.setEmail("Esther@vaak.org");
        appUser1.setPassword("prentenboek2");
        appUser1.setProfileImg("niceImg3.jpeg");
        appUser1.setLocked(true);
        appUser1.setAddress(address1);
        appUser1.setAppUserRole(AppUserRole.CUSTOMER);
        appUser1.setEnabled(true);
        appUser1.setFirstName("Esther");
        appUser1.setAppUserId(123L);
        Optional<AppUser> ofResult1 = Optional.<AppUser>of(appUser1);
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(ofResult1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{email}", "Esther@vaak.org");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"appUserId\":123,\"firstName\":\"Klaas\",\"lastName\":\"Vaak\",\"email\":\"klaasvaak@gmail.com\",\"password\":\"prentenboek"
                                        + "\",\"profileImg\":\"niceImg.jpg\",\"address\":{\"addressID\":1,\"appUsers\":[],\"streetName\":\"Street Name\","
                                        + "\"houseNumber\":10,\"city\":\"Groningen\",\"postalCode\":\"Postal Code\",\"country\":\"Meder;amd\",\"state\":\"Groningen\"},\"appUserRole"
                                        + "\":\"CUSTOMER\",\"locked\":true,\"enabled\":true,\"username\":\"Esther@vaak.org\",\"accountNonLocked\":false"
                                        + ",\"userImageLink\":\"niceImg3..jpeg\",\"authorities\":[{\"authority\":\"CUSTOMER\"}],\"accountNonExpired\":true,"
                                        + "\"credentialsNonExpired\":true}")));
    }

    @Test
    public void testGetAppUserByEmail2() throws Exception {
        Address address = new Address();
        address.setPostalCode("Postal Code");
        address.setStreetName("Street Name");
        address.setAddressID(1L);
        address.setCountry("Nederland");
        address.setAppUsers(new ArrayList<AppUser>());
        address.setCity("Friesland");
        address.setHouseNumber(10);
        address.setState("Sneek");

        AppUser appUser = new AppUser();
        appUser.setLastName("Van Schendel");
        appUser.setEmail("t.vanschendel@gmail.com");
        appUser.setPassword("benteHond");
        appUser.setProfileImg("4242faa/.jpeg");
        appUser.setLocked(true);
        appUser.setAddress(address);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Tijmen");
        appUser.setAppUserId(123L);
        Optional<AppUser> ofResult = Optional.<AppUser>of(appUser);
        when(this.appUserService.getUserByEmail(anyString())).thenReturn(ofResult);
        when(this.appUserRepository.findByEmail(anyString())).thenReturn(Optional.<AppUser>empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{email}", "t.vanschendel@gmail.com");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("Gebruiker met email t.vanschendel@gmail.com niet gevonden!")));
    }

    @Test
    public void testUploadUserProfileImage() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/users/{userProfileId}/image/upload",
                UUID.randomUUID());
        MockHttpServletRequestBuilder requestBuilder = postResult.param("file", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }
}

