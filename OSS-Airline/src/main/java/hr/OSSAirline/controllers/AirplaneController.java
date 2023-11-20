package hr.OSSAirline.controllers;

import hr.OSSAirline.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AirplaneController {
    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService){
        this.airplaneService=airplaneService;
    }
}
