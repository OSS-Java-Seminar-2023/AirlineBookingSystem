package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FlightController {

    public final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }
}
