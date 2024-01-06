package hr.OSSAirline.services;

import hr.OSSAirline.models.User;
import hr.OSSAirline.utils.MailConstants;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String body, String subject) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ossairline@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    public void sendPurchaseEmail(User user, String purchaseId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ossairline@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject(MailConstants.MAIL_PURCHASE_SUBJECT);
        message.setText(getPurchaseEmailMessage(user, purchaseId));
        javaMailSender.send(message);
    }

    private String getPurchaseEmailMessage(User user, String purchaseId) {
        return "Hello, " + user.getUsername() + "! \nYou can see you reservation on our web page using this code: \n" +
                purchaseId + "\nBest regards, OSS Airline";
    }
}
