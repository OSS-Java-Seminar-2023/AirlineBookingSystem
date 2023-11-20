package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.FlightRepository;
import hr.OSSAirline.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

    public final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService){

        this.flightService=flightService;
    }
}
