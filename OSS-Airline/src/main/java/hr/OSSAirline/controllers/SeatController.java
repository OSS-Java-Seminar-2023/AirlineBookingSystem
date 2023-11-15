package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SeatController {

    public final SeatRepository seatRepository;

    @Autowired
    public SeatController(SeatRepository seatRepository){
        this.seatRepository=seatRepository;
    }
}
