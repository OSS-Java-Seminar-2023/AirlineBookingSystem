package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.services.FlightService;
import hr.OSSAirline.services.PassengerService;
import hr.OSSAirline.services.SeatService;
import hr.OSSAirline.services.TicketPurchaseService;
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


    @PostMapping("/tickets/reserve")
    public String reserveTickets(@ModelAttribute("tickets") TicketForm ticketForm, Model model) {
        ticketPurchaseService.makePurchase(ticketForm);
        System.out.println("hello");
        return "redirect:/";
    }

    @Data
    public class TicketForm{
        private List<TicketDto> tickets;
        private List<String> passenger;
        private List<String> flight;
        private List<String> seat;
    }
}
