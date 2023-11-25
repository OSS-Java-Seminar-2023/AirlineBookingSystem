package hr.OSSAirline.controllers;

import hr.OSSAirline.models.Airport;
import hr.OSSAirline.repositories.AirportRepository;
import hr.OSSAirline.services.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;
    @GetMapping("/")
    public String home(Model model) {
        List<Airport> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        return "index";
    }
}
