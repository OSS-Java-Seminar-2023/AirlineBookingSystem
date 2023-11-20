package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.TicketRepository;
import hr.OSSAirline.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    public final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){

        this.ticketService=ticketService;
    }
}
