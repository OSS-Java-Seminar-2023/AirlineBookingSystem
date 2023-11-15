package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AirportController {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportController(AirportRepository airportRepository){
        this.airportRepository=airportRepository;
    }
}
