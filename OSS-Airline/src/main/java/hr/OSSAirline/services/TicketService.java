package hr.OSSAirline.services;

import hr.OSSAirline.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    public final TicketRepository ticketRepository;

}
