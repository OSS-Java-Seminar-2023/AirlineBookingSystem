package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.services.AirplaneService;
import hr.OSSAirline.services.AirportService;
import hr.OSSAirline.services.FlightService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        var flights = flightService.getAllFlights();
        var airports = airportService.getAllAirports();
        model.addAttribute("httpSession", session);
        model.addAttribute("flights", flightService.getFlightsToday());
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
        var pairFlightPrices = convertToFlightDto(flightForm);
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


    @Data
    public static class FlightForm {
        private String flightNumber;
        private String fromAirportId;
        private String toAirportId;
        private String airplaneId;
        private Date date;
        private LocalTime time;
        private String gate;
        private LocalTime duration;
        private Double firstClassPrice;
        private Double businessClassPrice;
        private Double economyClassPrice;

    }

    private Pair<FlightDto, Map<String, Float>> convertToFlightDto(FlightForm flightForm) {
        var flight = new FlightDto();
        flight.setFlightNumber(flightForm.flightNumber);
        flight.setFrom(airportService.getAirportById(flightForm.fromAirportId));
        flight.setTo(airportService.getAirportById(flightForm.toAirportId));
        flight.setAirplane(airplaneService.getAirplaneById(flightForm.airplaneId));
        flight.setDate(flightForm.date);
        flight.setTime(Time.valueOf(flightForm.time));
        flight.setGate(flightForm.gate);
        flight.setDuration(Time.valueOf(flightForm.duration));
        var prices = new HashMap<String, Float>();
        prices.put("firstClassPrice", flightForm.getFirstClassPrice().floatValue());
        prices.put("businessClassPrice", flightForm.getBusinessClassPrice().floatValue());
        prices.put("economyClassPrice", flightForm.getEconomyClassPrice().floatValue());

        return new Pair<>(flight, prices);
    }

}
