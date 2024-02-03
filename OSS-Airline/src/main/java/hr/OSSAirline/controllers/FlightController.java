package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.models.FlightForm;
import hr.OSSAirline.services.AirplaneService;
import hr.OSSAirline.services.AirportService;
import hr.OSSAirline.services.FlightService;
import hr.OSSAirline.utils.FlightFormConverter;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@Controller
@RequiredArgsConstructor
public class FlightController {

    public final FlightService flightService;
    public final AirportService airportService;
    public final AirplaneService airplaneService;


    @GetMapping("/flights-all")
    public String listFlights(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var flights = flightService.getFlightsToday();
        var airports = airportService.getAllAirports();
        model.addAttribute("httpSession", session);
        model.addAttribute("flights", flights);
        model.addAttribute("airports", airports);
        return "flight-list";
    }

    @GetMapping("/flights/create")
    public String showCreateFlightForm(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airports = airportService.getAllAirports();
        var airplanes = airplaneService.getAllAirplanes();

        model.addAttribute("httpSession", session);
        model.addAttribute("airports", airports);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("flightForm", new FlightForm());

        return "create-flight";
    }

    @PostMapping("/flights/create")
    public String createFlight(@ModelAttribute("flight") FlightForm flightForm, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var flightFormConverter = new FlightFormConverter(airportService,airplaneService);
        var pairFlightPrices = flightFormConverter.convertToFlightDto(flightForm);
        try {
            flightService.createFlight(pairFlightPrices.a, pairFlightPrices.b.get("firstClassPrice"), pairFlightPrices.b.get("businessClassPrice"), pairFlightPrices.b.get("economyClassPrice"));
        } catch (Exception e) {
            var airports = airportService.getAllAirports();
            var airplanes = airplaneService.getAllAirplanes();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("airports", airports);
            model.addAttribute("airplanes", airplanes);
            model.addAttribute("flightForm", new FlightForm());

            return "create-flight";
        }
        model.addAttribute("httpSession", session);
        return "redirect:/flights-all";
    }

    @PostMapping("/flights/delete/{flightId}")
    public String deleteFlight(@PathVariable("flightId") String flightId, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        try {
            flightService.deleteFlight(flightId);
        } catch (RuntimeException e) {
            var flights = flightService.getFlightsToday();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("flights", flights);
            return "flight-list";
        }
        model.addAttribute("httpSession", session);
        return "redirect:/flights-all";
    }

    @GetMapping("/flights/update/{flightId}")
    public String updateSelectedFlight(@PathVariable("flightId") String flightId, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var flight = flightService.getFlightById(flightId);
        var airports = airportService.getAllAirports();
        var airplanes = airplaneService.getAllAirplanes();
        model.addAttribute("airports", airports);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("httpSession", session);
        model.addAttribute("flight", flight);
        return "update-flight";
    }

    @PostMapping("/flights/update")
    public String updateFlight(@ModelAttribute("flight") FlightDto flight, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        try {
            flightService.updateFlight(flight.getId(), flight);
        } catch (RuntimeException e) {
            var flights = flightService.getFlightsToday();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("flights", flights);
            return "flight-list";
        }
        model.addAttribute("httpSession", session);
        return "redirect:/flights-all";
    }

    @PostMapping("/flights-all")
    public String filterFlights(@RequestParam("flightNumber") String flightNumber, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("date") String date, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;

        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var parsedDate = new java.util.Date();
        try {
            parsedDate = dateFormat.parse(date);
        }
        catch (ParseException e){
            System.out.println(e);
        }
        var flights = flightService.getAllFilteredFlights(flightNumber, from, to, parsedDate);
        var airports = airportService.getAllAirports();
        model.addAttribute("httpSession", session);
        model.addAttribute("flights", flights);
        model.addAttribute("airports", airports);
        return "flight-list";
    }

}
