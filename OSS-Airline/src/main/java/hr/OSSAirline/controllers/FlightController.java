package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

    public final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }
}
