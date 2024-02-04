package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.dto.SeatDto;
import hr.OSSAirline.services.FlightService;
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
    private final FlightService flightService;

    @PostMapping("/seats")
    public String getAllAvailableSeats(@RequestParam String flightId, Model model, HttpSession session) {
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if (x != null) return x;
        var availableSeats = ticketSeatService.getAllAvailableSeats(flightId);
        var flight = flightService.getFlightById(flightId);
        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("airplaneModel", flight.getAirplane().getModel());
        model.addAttribute("httpSession", session);
        return "seats";

    }

    @PostMapping("/tickets")
    public String bookSelectedSeats(@RequestParam("selectedSeats") List<String> seats, Model model, HttpSession session){
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if (x != null) return x;
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
