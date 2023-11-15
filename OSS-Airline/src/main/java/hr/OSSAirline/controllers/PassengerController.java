package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PassengerController {

    public final PassengerRepository passengerRepository;

    @Autowired
    public PassengerController(PassengerRepository passengerRepository){
        this.passengerRepository=passengerRepository;
    }
}
