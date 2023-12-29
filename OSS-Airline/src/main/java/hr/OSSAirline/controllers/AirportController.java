package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.services.AirportService;
import jakarta.servlet.http.HttpSession;
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
    public String home(Model model, HttpSession session) {
        List<AirportDto> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        model.addAttribute("httpSession",session);
        return "index";
    }
}
