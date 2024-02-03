package hr.OSSAirline.services;

import hr.OSSAirline.models.User;
import hr.OSSAirline.utils.MailConstants;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SimpleMailMessage message) {
        javaMailSender.send(message);
    }

    public void sendWelcomeEmail(String toEmail) throws MessagingException {
        var message = new SimpleMailMessage();
        message.setFrom("ossairline@gmail.com");
        message.setTo(toEmail);
        message.setSubject(MailConstants.MAIL_SUBJECT);
        message.setText(MailConstants.MAIL_BODY);
        sendEmail(message);
    }

    public void sendReservationMail(User user) {
        var message = new SimpleMailMessage();
        message.setFrom("ossairline@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject(MailConstants.MAIL_RESERVATION_SUBJECT);
        message.setText(getReservationEmailMessage(user));
        sendEmail(message);
    }

    private String getReservationEmailMessage(User user) {
        return "Hello, " + user.getUsername() + "! \nThank you for your reservation \n Best regards, OSS Airline";
    }
}
