package hr.OSSAirline.services;

import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
}
