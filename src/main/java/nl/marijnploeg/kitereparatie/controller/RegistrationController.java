package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.exception.ApiExceptions.ApiRequestException;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import nl.marijnploeg.kitereparatie.request.RegistrationRequest;
import nl.marijnploeg.kitereparatie.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @CrossOrigin
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @CrossOrigin
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
