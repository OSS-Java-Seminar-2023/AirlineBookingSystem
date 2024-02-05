package hr.OSSAirline.controllers;

import hr.OSSAirline.models.TicketForm;
import hr.OSSAirline.repositories.UserRepository;
import hr.OSSAirline.services.EmailService;
import hr.OSSAirline.services.TicketReservationService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TicketReservationController {
    public final TicketReservationService ticketReservationService;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @PostMapping("/tickets/reserve")
    public String reserveTickets(@ModelAttribute("tickets") TicketForm ticketForm, Model model, HttpSession session) {
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if (x != null) return x;
        var userName = session.getAttribute("userName").toString();
        ticketReservationService.makeReservation(ticketForm, userName);
        var user = userRepository.findByUsername(userName);
        emailService.sendReservationMail(user.get());
        model.addAttribute("reservation", String.format(userName + " thank you for your reservation!\nPlease check your email!"));
        model.addAttribute("httpSession", session);
        return "index";
    }

}
