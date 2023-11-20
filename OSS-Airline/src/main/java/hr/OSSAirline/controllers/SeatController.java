package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.SeatRepository;
import hr.OSSAirline.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SeatController {

    public final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService){

        this.seatService=seatService;
    }
}
