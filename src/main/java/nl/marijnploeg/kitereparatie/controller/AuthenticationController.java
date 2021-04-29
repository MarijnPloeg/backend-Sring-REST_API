package nl.marijnploeg.kitereparatie.controller;

import nl.marijnploeg.kitereparatie.payload.AuthenticationRequest;
import nl.marijnploeg.kitereparatie.payload.AuthenticationResponse;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import nl.marijnploeg.kitereparatie.service.CustomerService;
import nl.marijnploeg.kitereparatie.service.JwtUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        }
        catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails userDetails = appUserService
                .loadUserByUsername(email);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
