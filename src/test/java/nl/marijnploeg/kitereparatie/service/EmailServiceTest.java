package nl.marijnploeg.kitereparatie.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    @Test
    public void testSend() throws MailException {
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        this.emailService.send("hans@lieben.nl", "marijnploeg@gmail.com");
        verify(this.javaMailSender).createMimeMessage();
        verify(this.javaMailSender).send((MimeMessage) any());
    }

    @Test
    public void testSend2() throws MailException {
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        this.emailService.send("utf-8", "marijnploeg@gmail.com");
        verify(this.javaMailSender).createMimeMessage();
        verify(this.javaMailSender).send((MimeMessage) any());
    }

    @Test
    public void testSend3() throws MailException {
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        assertThrows(IllegalStateException.class,
                () -> this.emailService.send("Confirm your email", "marijnploeg@gmail.com"));
        verify(this.javaMailSender).createMimeMessage();
    }
}

