package nl.marijnploeg.kitereparatie.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AmazonConfig.class})
@ExtendWith(SpringExtension.class)
public class AmazonConfigTest {

//    TODO: Write test here

    @Autowired
    private AmazonConfig amazonConfig;

    @Test
    public void testS3() {
        this.amazonConfig.s3();
    }
}

