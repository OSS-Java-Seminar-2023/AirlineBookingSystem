package hr.OSSAirline.services;

import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.mappers.AirportMapper;
import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.models.Airport;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.repositories.AirportRepository;
import hr.OSSAirline.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightAirportService {
    public final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    public final AirportRepository airportRepository;

    public List<FlightDto> getFlights(String from, String to, Date date){

        var from_airport = airportRepository.findByName(from).orElseThrow(() -> new RuntimeException("No airport found"));
        var to_airport = airportRepository.findByName(to).orElseThrow(() -> new RuntimeException("No airport found"));

        return flightRepository.searchFlightsByFromAndToAndDate(from_airport,to_airport,date).stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

}
