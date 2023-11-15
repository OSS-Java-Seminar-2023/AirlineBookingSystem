package hr.OSSAirline.services;

import hr.OSSAirline.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    public final TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }
}
