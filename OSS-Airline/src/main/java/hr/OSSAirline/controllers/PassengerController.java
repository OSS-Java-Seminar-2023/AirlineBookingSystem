package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PassengerController {

    public final PassengerRepository passengerRepository;

    @Autowired
    public PassengerController(PassengerRepository passengerRepository){
        this.passengerRepository=passengerRepository;
    }
}
