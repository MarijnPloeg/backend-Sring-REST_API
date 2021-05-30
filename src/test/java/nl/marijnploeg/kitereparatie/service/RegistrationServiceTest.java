package nl.marijnploeg.kitereparatie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.marijnploeg.kitereparatie.email.EmailSender;
import nl.marijnploeg.kitereparatie.exception.ApiExceptions.ApiRequestException;
import nl.marijnploeg.kitereparatie.request.RegistrationRequest;
import nl.marijnploeg.kitereparatie.security.registration.EmailValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserService.class, RegistrationService.class, EmailValidator.class,
        ConfirmationTokenService.class})
@ExtendWith(SpringExtension.class)
public class RegistrationServiceTest {
    @MockBean
    private AppUserService appUserService;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private EmailSender emailSender;

    @MockBean
    private EmailValidator emailValidator;

    @Autowired
    private RegistrationService registrationService;

    @Test
    public void testRegister() {
        when(this.emailValidator.test(anyString())).thenReturn(true);
        doNothing().when(this.emailSender).send(anyString(), anyString());
        when(this.appUserService.signUpUser(any())).thenReturn("foo");
        assertEquals("foo",
                this.registrationService.register(new RegistrationRequest("Marijn", "Ploeg", "marijnploeg@gmail.com", "password")));
        verify(this.appUserService).signUpUser(any());
        verify(this.emailSender).send(anyString(), anyString());
        verify(this.emailValidator).test(anyString());
    }

    @Test
    public void testRegister2() {
        when(this.emailValidator.test(anyString())).thenReturn(false);
        doNothing().when(this.emailSender).send(anyString(), anyString());
        when(this.appUserService.signUpUser(any())).thenReturn("foo");
        assertThrows(ApiRequestException.class, () -> this.registrationService
                .register(new RegistrationRequest("Martijn", "Schipper", "martijnschipper@gmail.com", "password")));
        verify(this.emailValidator).test(anyString());
    }

    @Test
    public void testRegister3() {
        when(this.emailValidator.test(anyString())).thenReturn(true);
        doNothing().when(this.emailSender).send(anyString(), anyString());
        when(this.appUserService.signUpUser(any())).thenReturn("foo");
        RegistrationRequest registrationRequest = mock(RegistrationRequest.class);
        when(registrationRequest.getPassword()).thenReturn("foo");
        when(registrationRequest.getLastname()).thenReturn("foo");
        when(registrationRequest.getFirstname()).thenReturn("foo");
        when(registrationRequest.getEmail()).thenReturn("foo");
        assertEquals("foo", this.registrationService.register(registrationRequest));
        verify(this.appUserService).signUpUser(any());
        verify(this.emailSender).send(anyString(), anyString());
        verify(this.emailValidator).test(anyString());
        verify(registrationRequest, times(2)).getFirstname();
        verify(registrationRequest).getPassword();
        verify(registrationRequest).getLastname();
        verify(registrationRequest, times(3)).getEmail();
    }
}

