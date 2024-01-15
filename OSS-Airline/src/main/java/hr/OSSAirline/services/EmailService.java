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

    public void compileEmail(String toEmail, String body, String subject) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ossairline@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        sendEmail(message);
    }

    public void sendReservationMail(User user, String reservationId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ossairline@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject(MailConstants.MAIL_RESERVATION_SUBJECT);
        message.setText(getReservationEmailMessage(user, reservationId));
        sendEmail(message);
    }

    private String getReservationEmailMessage(User user, String reservationId) {
        return "Hello, " + user.getUsername() + "! \nYou can see you reservation on our web page using this code: \n" +
                reservationId + "\nBest regards, OSS Airline";
    }
}
