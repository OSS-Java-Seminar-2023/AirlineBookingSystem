package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SeatController {

    public final SeatRepository seatRepository;

    @Autowired
    public SeatController(SeatRepository seatRepository){

        this.seatRepository=seatRepository;
    }
}
