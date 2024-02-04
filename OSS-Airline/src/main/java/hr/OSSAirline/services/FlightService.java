package hr.OSSAirline.services;

import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.repositories.FlightRepository;
import hr.OSSAirline.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
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

    public void updateFlight(String id, FlightDto flight) {
        if (flightRepository.existsById(id) && !ticketRepository.existsTicketsByFlight_Id(id)) {
            flight.setId(id);
            flightRepository.save(flightMapper.toEntity(flight));
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

    public List<Flight> filterFlightsByDate(List<Flight> flights, java.util.Date date){
        return flights.stream().filter(flight -> flight.getDate().equals(date)).toList();
    }

    public List<Flight> getAllFilteredFlights(String flightNumber, String fromAirportName, String toAirportName, java.util.Date date){
        List<Flight> flights = new ArrayList<>();
        if(!flightNumber.isBlank() && fromAirportName.isBlank() && toAirportName.isBlank()){
            flights.addAll(flightRepository.getFlightByFlightNumber(flightNumber));
        }
        else if(!flightNumber.isBlank() && !fromAirportName.isBlank() && toAirportName.isBlank()){
            flights.addAll(flightRepository.getFlightByFlightNumberAndFrom_Name(flightNumber, fromAirportName));
        } else if(!flightNumber.isBlank() && !fromAirportName.isBlank()) {
            flights.addAll(flightRepository.getFlightByFlightNumberAndFrom_NameAndTo_Name(flightNumber, fromAirportName, toAirportName));
        } else if(flightNumber.isBlank() && fromAirportName.isBlank() && toAirportName.isBlank() && date == null){
            flights.addAll(flightRepository.getFlightsToday());
        }

        if(date != null && !flights.isEmpty()){
            filterFlightsByDate(flights, date);
        } else if(date != null){
            flights.addAll(flightRepository.getFlightsByDate(date));
        }

        return flights;
    }
}
