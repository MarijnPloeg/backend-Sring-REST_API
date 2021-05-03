package nl.marijnploeg.kitereparatie.service;

import lombok.AllArgsConstructor;
import nl.marijnploeg.kitereparatie.exception.EmailNotFoundException;
import nl.marijnploeg.kitereparatie.model.ConfirmationToken;
import nl.marijnploeg.kitereparatie.model.UserRoles.AppUser;
import nl.marijnploeg.kitereparatie.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
//    Overriding this method with email as username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

//    Method get username is set to return email. Application does not use username
    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getUsername())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("Email already taken!");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

//        TODO: Send eMail

        confirmationTokenService.safeConfirmationToken(confirmationToken);
        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }


//    public Set<Authority> getAuthorities(String email) {
//        if (!appUserRepository.existsByEmail(email)) throw new EmailNotFoundException(email);
//        AppUser appUser = appUserRepository.findCustomerByEmail(email);
//        return appUser.getAuthorities();
//    }
//
//    public void addAuthority(String email, String authority) {
//        if (!appUserRepository.existsByEmail(email)) throw new EmailNotFoundException(email);
//        AppUser appUser = appUserRepository.findCustomerByEmail(email);
//        appUser.addAuthority(new Authority(email, authority));
//        appUserRepository.save(appUser);
//    }
//
//    public void removeAuthority(String email, String authority) {
//        if (!appUserRepository.existsByEmail(email)) throw new EmailNotFoundException(email);
//        AppUser appUser = appUserRepository.findCustomerByEmail(email);
//        Authority authorityToRemove = appUser.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
//        appUser.removeAuthority(authorityToRemove);
//        appUserRepository.save(appUser);
//    }
//
//    public UserDetails loadUserByEmail(String email) {
//        AppUser appUser = appUserRepository.findCustomerByEmail(email);
//        if (appUser == null) {
//            throw new EmailNotFoundException(email);
//        }
//
//        String password = appUser.getPassword();
//
//        Set<Authority> authorities = appUser.getAuthorities();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority: authorities) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(email, password, grantedAuthorities);
//    }
}
