package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.PassengerRepository;
import hr.OSSAirline.services.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PassengerController {

    private  final PassengerService passengerService;

}
