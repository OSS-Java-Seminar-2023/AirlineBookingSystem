package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.AirportRepository;
import hr.OSSAirline.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService){
        this.airportService=airportService;
    }
}
