package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.dto.SeatDto;
import hr.OSSAirline.services.TicketSeatService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketSeatController {

    private final TicketSeatService ticketSeatService;

    @PostMapping("/seats")
    public String getAllAvailableSeats(@RequestParam String flightId, Model model, HttpSession session) {
//        try {
//            SecurityCheck.isUserNotLoggedInReturnToLogin(session);
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            model.addAttribute("httpSession",session);
//            return "user_login";
//        }
        var availableSeats = ticketSeatService.getAllAvailableSeats(flightId);
        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("httpSession", session);
        return "seats";

    }

    @PostMapping("/tickets")
    public String bookSelectedSeats(@RequestParam("selectedSeats") List<String> seats, Model model, HttpSession session){
//        try {
//            SecurityCheck.isUserNotLoggedInReturnToLogin(session);
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            model.addAttribute("httpSession",session);
//            return "user_login";
//        }
        var selectedSeats = ticketSeatService.getSeatsByIds(seats.stream().toList());
        var passengers = new ArrayList<>();
        for (int i = 0; i < selectedSeats.size(); i++) {
            passengers.add(new PassengerDto());
        }
        var totalPrice = selectedSeats.stream()
                .mapToDouble(SeatDto::getSeatPrice)
                .sum();
        model.addAttribute("passengers", passengers);
        model.addAttribute("selectedSeats", selectedSeats);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("httpSession",session);
        return "tickets";
    }
}
