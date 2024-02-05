package hr.OSSAirline.services;

import hr.OSSAirline.dto.SeatDto;
import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.mappers.SeatMapper;
import hr.OSSAirline.mappers.TicketMapper;
import hr.OSSAirline.repositories.SeatRepository;
import hr.OSSAirline.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketSeatService {
    public final TicketRepository ticketRepository;
    public final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final TicketMapper ticketMapper;

    public List<SeatDto> getAllAvailableSeats(String flightId) {
        var allSeats = seatRepository.findByFlightId(flightId).stream()
                .map(seatMapper::toDto)
                .toList();

        var takenTickets = ticketRepository.findByFlightId(flightId).stream()
                .map(ticketMapper::toDto)
                .toList();

        var takenSeats = takenTickets.stream()
                .map(TicketDto::getSeat)
                .toList();

        return allSeats.stream()
                .filter(seat -> !takenSeats.contains(seat))
                .collect(Collectors.toList());
    }

    public List<SeatDto>  getSeatsByIds(List<String> seat_ids){
        return seatRepository.findByIdIn(seat_ids).stream()
                .map(seatMapper::toDto)
                .toList();
    }
}
