package hr.OSSAirline.controllers;

import hr.OSSAirline.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class FlightController {

    public final FlightService flightService;
}
