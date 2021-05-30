package nl.marijnploeg.kitereparatie.security.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PasswordEncoder.class})
@ExtendWith(SpringExtension.class)
public class PasswordEncoderTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testBCryptPasswordEncoder() {
        // TODO: This test is not written.

        this.passwordEncoder.bCryptPasswordEncoder();
    }
}

