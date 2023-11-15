package hr.OSSAirline.services;

import hr.OSSAirline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    public final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }
}
