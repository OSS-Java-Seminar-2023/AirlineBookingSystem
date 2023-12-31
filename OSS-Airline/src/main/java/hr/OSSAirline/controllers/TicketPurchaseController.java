package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.repositories.UserRepository;
import hr.OSSAirline.services.*;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TicketPurchaseController {
    public final TicketPurchaseService ticketPurchaseService;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @PostMapping("/tickets/reserve")
    public String reserveTickets(@ModelAttribute("tickets") TicketForm ticketForm, Model model, HttpSession session) {
        //        try {
//            SecurityCheck.isUserNotLoggedInReturnToLogin(session);
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            model.addAttribute("httpSession",session);
//            return "user_login";
//        }
        var userName = session.getAttribute("userName").toString();
        var purchaseId = ticketPurchaseService.makePurchase(ticketForm,userName);
        var user = userRepository.findByUsername(userName);
        emailService.sendPurchaseEmail(user.get(), purchaseId);
        model.addAttribute("reservation", String.format(userName + " thank you for your reservation!\nPlease check your email!"));
        model.addAttribute("httpSession",session);
        return "index";
    }

    @Data
    public class TicketForm{
        private List<TicketDto> tickets;
        private List<String> passenger;
        private List<String> flight;
        private List<String> seat;
        private List<Float> price;
    }
}
