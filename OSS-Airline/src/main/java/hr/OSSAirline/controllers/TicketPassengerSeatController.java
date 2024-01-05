package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.dto.SeatDto;
import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.services.TicketPassengerSeatService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class TicketPassengerSeatController {
    private final TicketPassengerSeatService ticketPassengerSeatService;
    @PostMapping("/tickets/save")
    public String saveTickets(@ModelAttribute("passengerForm") PassengerForm passengerForm, @RequestParam("seats") List<String> seats, Model model, HttpSession session) {
        var passengers = passengerForm.getPassengers();
        var passengers_map = IntStream.range(0, seats.size())
                .boxed()
                .collect(Collectors.toMap(
                        seats::get,
                        passengers::get
                ));

        var ticket_list = ticketPassengerSeatService.proccessTicket(passengers_map);
        model.addAttribute("httpSession",session);
        model.addAttribute("ticketList", ticket_list);

        return "ticket_list";
    }
    @Data
    private class PassengerForm {
        private List<PassengerDto> passengers;
    }
}


