package hr.OSSAirline.services;


import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.repositories.AirportRepository;
import hr.OSSAirline.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightAirportService {
    public final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    public final AirportRepository airportRepository;

    public HashMap<String,List<FlightDto>> getFlights(String from, String to, Date date){

        var from_airport = airportRepository.findByName(from).orElseThrow(() -> new RuntimeException("Wrong airport name!"));
        var to_airport = airportRepository.findByName(to).orElseThrow(() -> new RuntimeException("Wrong airport name!"));

        var flights_before= flightRepository.findAllFlightsForFirstDateBeforeGivenDate(from_airport,to_airport,date).stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());

        var flights_after = flightRepository.findAllFlightsForFirstDateAfterGivenDate(from_airport,to_airport,date).stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());

        var flights_date = flightRepository.searchFlightsByFromAndToAndDate(from_airport,to_airport,date).stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());

        var flights_map = new HashMap<String,List<FlightDto>>();
        flights_map.put("flights_before", flights_before);
        flights_map.put("flights_date",flights_date);
        flights_map.put("flights_after",flights_after);
        return flights_map;
    }

}
