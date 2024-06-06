package br.com.techie.shoppingstore.AP003.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import static org.mockito.Mockito.*;

import java.util.Base64;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private SpringTemplateEngine templateEngine;

    private MimeMessage mimeMessage;

    @BeforeEach
    public void setUp() {
        mimeMessage = mock(MimeMessage.class);
        lenient().when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    public void testRegistrationConfirmationEmail() throws MessagingException {
        String email = "test@example.com";
        String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());

        // Using spy to mock internal method call
        EmailService spyEmailService = spy(emailService);
        doNothing().when(spyEmailService).sendRegistrationConfirmationRequest(email, encodedEmail);

        spyEmailService.RegistrationConfirmationEmail(email);

        verify(spyEmailService, times(1)).sendRegistrationConfirmationRequest(email, encodedEmail);
    }

    @Test
    public void testSendRegistrationConfirmationRequest() throws MessagingException {
        String destination = "test@example.com";
        String code = "encoded-email";

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Bem-vindo ao ParkApi");
        context.setVariable("texto", "Precisamos que você confirme seu cadastro clicando no link abaixo");
        context.setVariable("linkConfirmacao", "http://localhost:8080/api/v1/usuarios/confirmacao/cadastro?codigo=" + code);

        String htmlContent = "<html>Mocked HTML content</html>";
        doReturn(htmlContent).when(templateEngine).process(eq("email/confirmacao"), any(Context.class));

        emailService.sendRegistrationConfirmationRequest(destination, code);

        verify(javaMailSender, times(1)).createMimeMessage();
        verify(templateEngine, times(1)).process(eq("email/confirmacao"), any(Context.class));

        mimeMessageHelper.setTo(destination);
        mimeMessageHelper.setText(htmlContent, true);
        mimeMessageHelper.setSubject("Confirmação de cadastro");
        mimeMessageHelper.setFrom("nao-responder@park.com.br");

        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendOrderResetPassword() throws MessagingException {
        String destination = "test@example.com";
        String token = "reset-token";

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Redefinição de senha");
        context.setVariable("texto", "Para redefinir sua senha, clique no link para lhe direcionar. Token valido por 10 min. quando exigido no formulário");
        context.setVariable("linkConfirmacao", "http://localhost:8080/api/v1/password-reset?token=" + token);

        String htmlContent = "<html>Mocked HTML content</html>";
        doReturn(htmlContent).when(templateEngine).process(eq("email/confirmacao"), any(Context.class));

        emailService.sendOrderResetPassword(destination, token);

        verify(javaMailSender, times(1)).createMimeMessage();
        verify(templateEngine, times(1)).process(eq("email/confirmacao"), any(Context.class));

        mimeMessageHelper.setTo(destination);
        mimeMessageHelper.setText(htmlContent, true);
        mimeMessageHelper.setSubject("Redefinição de senha");
        mimeMessageHelper.setFrom("nao-responder@park.com.br");

        verify(javaMailSender, times(1)).send(mimeMessage);
    }
}
