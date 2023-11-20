package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.PassengerRepository;
import hr.OSSAirline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PassengerController {

    public final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService){
        this.passengerService=passengerService;
    }
}
