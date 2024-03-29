package hr.OSSAirline.controllers;


import hr.OSSAirline.services.FlightAirportService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            model.addAttribute("httpSession",session);
        }
        catch (ParseException e){
            System.out.println(e);
        }
        catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession",session);
            return "index";
        }
        return "flights";
    }

}
