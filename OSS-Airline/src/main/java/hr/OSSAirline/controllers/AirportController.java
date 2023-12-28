package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.services.AirportService;
import lombok.RequiredArgsConstructor;
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
        List<AirportDto> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        return "index";
    }
}
