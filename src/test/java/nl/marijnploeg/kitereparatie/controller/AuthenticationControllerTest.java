package nl.marijnploeg.kitereparatie.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.payload.AuthenticationRequest;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import nl.marijnploeg.kitereparatie.service.JwtUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthenticationController.class})
@ExtendWith(SpringExtension.class)
public class AuthenticationControllerTest {
    @MockBean
    private AppUserService appUserService;

    @Autowired
    private AuthenticationController authenticationController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void testAuthenticated() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/authenticated");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("authentication",
                String.valueOf(Authentication.RSA));
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAuthenticated2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/authenticated", "Uri Vars");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("authentication",
                String.valueOf(Authentication.RSA));
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateAuthenticationToken() throws Exception {
        when(this.jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) any()))
                .thenReturn("foo");
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(this.appUserService.loadUserByUsername(anyString()))
                .thenReturn(new AppUser("Jane", "Doe", "jane.doe@example.org", "iloveyou", AppUserRole.CUSTOMER));

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("jane.doe@example.org");
        authenticationRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{\"jwt\":\"foo\"}")));
    }
}

