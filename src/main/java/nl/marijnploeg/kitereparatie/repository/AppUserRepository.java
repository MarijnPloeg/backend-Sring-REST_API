package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository {

    Optional<AppUser> findByEmail(String email);
}
