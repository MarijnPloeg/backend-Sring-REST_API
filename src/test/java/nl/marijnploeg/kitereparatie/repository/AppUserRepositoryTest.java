package nl.marijnploeg.kitereparatie.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.marijnploeg.kitereparatie.model.UserRoles.AppUser;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {AppUserRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void testFindByEmail() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        AppUser appUser = new AppUser();
        appUser.setLastName("Doe");
        appUser.setEmail("jane.doe@example.org");
        appUser.setPassword("iloveyou");
        appUser.setLocked(true);
        appUser.setId(123L);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");

        AppUser appUser1 = new AppUser();
        appUser1.setLastName("Doe");
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setPassword("iloveyou");
        appUser1.setLocked(true);
        appUser1.setId(123L);
        appUser1.setAppUserRole(AppUserRole.CUSTOMER);
        appUser1.setEnabled(true);
        appUser1.setFirstName("Jane");
        this.appUserRepository.<AppUser>save(appUser);
        this.appUserRepository.<AppUser>save(appUser1);
        this.appUserRepository.findByEmail("foo");
    }

    @Test
    public void testEnableAppUser() {
        AppUser appUser = new AppUser();
        appUser.setLastName("Doe");
        appUser.setEmail("jane.doe@example.org");
        appUser.setPassword("iloveyou");
        appUser.setLocked(true);
        appUser.setId(123L);
        appUser.setAppUserRole(AppUserRole.CUSTOMER);
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");

        AppUser appUser1 = new AppUser();
        appUser1.setLastName("Doe");
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setPassword("iloveyou");
        appUser1.setLocked(true);
        appUser1.setId(123L);
        appUser1.setAppUserRole(AppUserRole.CUSTOMER);
        appUser1.setEnabled(true);
        appUser1.setFirstName("Jane");
        this.appUserRepository.<AppUser>save(appUser);
        this.appUserRepository.<AppUser>save(appUser1);
        assertEquals(0, this.appUserRepository.enableAppUser("foo"));
    }
}

