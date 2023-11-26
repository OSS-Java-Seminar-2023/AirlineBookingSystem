package hr.OSSAirline.controllers;


import hr.OSSAirline.models.Flight;
import hr.OSSAirline.services.AirportService;
import hr.OSSAirline.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class FlightController {

    public final FlightService flightService;
    public final AirportService airportService;

    @PostMapping("/flights")
    public String searchFlights(@RequestParam String from, @RequestParam String to, @RequestParam String date, Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date();
        try {
            parsedDate = dateFormat.parse(date);
        }
        catch (ParseException ex){
            System.out.println(ex);
        }
        var to_id = airportService.getAirportByName(to);
        var from_id = airportService.getAirportByName(from);
        List<Flight> flights = flightService.getFlights(from_id, to_id, parsedDate);
        model.addAttribute("flights", flights);
        return "flights";
    }
}
