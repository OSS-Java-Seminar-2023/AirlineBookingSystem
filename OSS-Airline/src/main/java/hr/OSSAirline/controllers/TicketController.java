package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    public final TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository){

        this.ticketRepository=ticketRepository;
    }
}
