package hr.OSSAirline.controllers;


import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.models.PassengerForm;
import hr.OSSAirline.services.TicketPassengerSeatService;
import hr.OSSAirline.services.TicketSeatService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class TicketPassengerSeatController {
    private final TicketPassengerSeatService ticketPassengerSeatService;
    private final TicketSeatService ticketSeatService;
    @PostMapping("/tickets/save")
    public String saveTickets(@ModelAttribute("passengerForm") PassengerForm passengerForm, @RequestParam("seats") List<String> seats, Model model, HttpSession session) {
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if (x != null) return x;
        var passengers = passengerForm.getPassengers();
        var passengers_map = IntStream.range(0, seats.size())
                .boxed()
                .collect(Collectors.toMap(
                        seats::get,
                        passengers::get
                ));
        try{
            var ticket_list = ticketPassengerSeatService.proccessTicket(passengers_map);
            var totalPrice = ticket_list.stream()
                    .mapToDouble(TicketDto::getTicketPrice)
                    .sum();
            model.addAttribute("httpSession", session);
            model.addAttribute("ticketList", ticket_list);
            model.addAttribute("totalPrice", totalPrice);
        }
        catch (RuntimeException e){
            var selectedSeats = ticketSeatService.getSeatsByIds(seats.stream().toList());
            model.addAttribute("passengers", passengers);
            model.addAttribute("selectedSeats", selectedSeats);
            model.addAttribute("httpSession",session);
            model.addAttribute("error",e.getMessage());
            return "tickets";
        }

        return "ticket_list";
    }
}


