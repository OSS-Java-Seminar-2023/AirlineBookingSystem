package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.SeatRepository;
import hr.OSSAirline.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SeatController {

    public final SeatService seatService;
}
