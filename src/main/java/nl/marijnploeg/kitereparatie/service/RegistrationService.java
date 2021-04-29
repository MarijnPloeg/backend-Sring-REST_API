package nl.marijnploeg.kitereparatie.service;

import lombok.AllArgsConstructor;
import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.model.AppUserRole;
import nl.marijnploeg.kitereparatie.request.RegistrationRequest;
import nl.marijnploeg.kitereparatie.security.EmailValidator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) { throw new IllegalStateException("Email not valid!");}

        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.CUSTOMER
                )
        );
    }


}
