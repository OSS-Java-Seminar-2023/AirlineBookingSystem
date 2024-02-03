package hr.OSSAirline.services;

import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.mappers.TicketMapper;
import hr.OSSAirline.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

}
