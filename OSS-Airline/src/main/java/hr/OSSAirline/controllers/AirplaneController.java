package hr.OSSAirline.controllers;

import hr.OSSAirline.services.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;

}
