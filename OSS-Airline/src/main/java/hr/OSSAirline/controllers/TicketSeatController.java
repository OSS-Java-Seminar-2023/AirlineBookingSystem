package hr.OSSAirline.controllers;

import hr.OSSAirline.services.TicketSeatService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TicketSeatController {

    private final TicketSeatService ticketSeatService;

    @PostMapping("/seats")
    public String getAllAvailableSeats(@RequestParam String flightId, Model model, HttpSession session) {
        try {
            SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession",session);
            return "user_login";
        }
        var availableSeats = ticketSeatService.getAllAvailableSeats(flightId);
        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("httpSession", session);
        return "seats";

    }
}
