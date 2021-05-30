package nl.marijnploeg.kitereparatie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtUtil.class})
@ExtendWith(SpringExtension.class)
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testExtractEmail() {
        // TODO: Write assertions for this test

        this.jwtUtil.extractEmail("ABC123");
    }

    @Test
    public void testGenerateToken() {
        assertEquals(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW5lLmRvZUBleGFtcGxlLm9yZyIsImV4cCI6MTYyMjU0ODY5NCwiaWF0IjoxNjIxNjg0Njk0fQ"
                        + ".OydqLE2nqsmnHQPV7iAWY6Bz88c-Z7Ztwf2efE_7obs",
                this.jwtUtil
                        .generateToken(new AppUser("Marijn", "Ploeg", "marijnnploeg@gmail.com", "password", AppUserRole.CUSTOMER)));
        assertEquals(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW5lLmRvZUBleGFtcGxlLm9yZyIsImV4cCI6MTYyMjU0ODY5NCwiaWF0IjoxNjIxNjg0Njk0fQ"
                        + ".OydqLE2nqsmnHQPV7iAWY6Bz88c-Z7Ztwf2efE_7obs",
                this.jwtUtil
                        .generateToken(new AppUser("Martijn", "Schipper", "martijn.schipper@gmail.com", "password", AppUserRole.CUSTOMER)));
        assertEquals(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW5lLmRvZUBleGFtcGxlLm9yZyIsImV4cCI6MTYyMjU0ODY5NCwiaWF0IjoxNjIxNjg0Njk0fQ"
                        + ".OydqLE2nqsmnHQPV7iAWY6Bz88c-Z7Ztwf2efE_7obs",
                this.jwtUtil.generateToken(
                        new AppUser("Michiel", "Schipper", "michielschipper@gmail.com", "password", AppUserRole.CUSTOMER)));
    }

    @Test
    public void testValidateToken() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        this.jwtUtil.validateToken("ABC123",
                new AppUser("Marijn", "Ploeg", "marijnnploeg@gmail.com", "password", AppUserRole.CUSTOMER));
    }
}

