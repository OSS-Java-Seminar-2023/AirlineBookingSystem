package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.dto.SeatDto;
import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.services.TicketPassengerSeatService;
import hr.OSSAirline.services.TicketSeatService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
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
        var passengers = passengerForm.getPassengers();
        var passengers_map = IntStream.range(0, seats.size())
                .boxed()
                .collect(Collectors.toMap(
                        seats::get,
                        passengers::get
                ));
        try{
            var ticket_list = ticketPassengerSeatService.proccessTicket(passengers_map); //TODO  fix error throw form function DONE
            model.addAttribute("httpSession", session);
            model.addAttribute("ticketList", ticket_list);
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
    @Data
    private class PassengerForm {
        private List<PassengerDto> passengers;
    }
}


