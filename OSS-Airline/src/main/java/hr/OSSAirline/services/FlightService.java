package hr.OSSAirline.services;

import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.repositories.FlightRepository;
import hr.OSSAirline.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final SeatService seatService;
    private final TicketRepository ticketRepository;

    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .toList();

    }

    public void createFlight(FlightDto flightDto, Float firstClassPrice, Float businessClassPrice, Float economyClassPrice) {
        var flight = flightRepository.save(flightMapper.toEntity(flightDto));
        seatService.generateSeatsForFlight(flight, firstClassPrice, businessClassPrice, economyClassPrice);
    }

    public Flight updateFlight(String id, FlightDto flight) {
        if (flightRepository.existsById(id) && !ticketRepository.existsTicketsByFlight_Id(id)) {
            flight.setId(id);
            return flightRepository.save(flightMapper.toEntity(flight));
        } else {
            throw new RuntimeException("Can not update flight that has reserved tickets.");
        }
    }

    public void deleteFlight(String id) {
        if (flightRepository.existsById(id) && !ticketRepository.existsTicketsByFlight_Id(id)) {
            flightRepository.deleteById(id);
        } else {
            throw new RuntimeException("Can not delete flight that has reserved tickets.");
        }
    }

    public List<Flight> getFlightsToday() {
        return flightRepository.getFlightsToday();
    }

    public Flight getFlightById(String id) {
        return flightRepository.getReferenceById(id);
    }
}
