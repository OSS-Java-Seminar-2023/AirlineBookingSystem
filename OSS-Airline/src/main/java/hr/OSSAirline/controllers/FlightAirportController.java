package hr.OSSAirline.controllers;


import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.models.Airport;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.services.AirportService;
import hr.OSSAirline.services.FlightAirportService;
import hr.OSSAirline.services.FlightService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class FlightAirportController {

    public final FlightAirportService flightService;

    @PostMapping("/flights")
    public String searchFlights(@RequestParam String from, @RequestParam String to, @RequestParam String date, Model model, HttpSession session) {
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var parsedDate = new Date();
        try {
            parsedDate = dateFormat.parse(date);
            var flights_map = flightService.getFlights(from, to, parsedDate);
            model.addAttribute("flights_map", flights_map);
        }
        catch (ParseException e){
            System.out.println(e);
        }
        catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
        return "flights";
    }

}
