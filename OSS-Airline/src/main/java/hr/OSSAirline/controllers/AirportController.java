package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.services.AirportService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        var airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        model.addAttribute("httpSession",session);
        return "index";
    }

    @GetMapping("/airports")
    public String getAllAirports(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airports = airportService.getAllAirports();
        model.addAttribute("httpSession", session);
        model.addAttribute("airports", airports);
        return "airports";
    }

    @GetMapping("/airports/create")
    public String createAirport(Model model,HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("httpSession", session);
        model.addAttribute("airport", new AirportDto());
        return "create-airport";
    }

    @PostMapping("/airports/create")
    public String createAirport(@ModelAttribute("airport") AirportDto airport, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        try{
            airportService.save(airport);
        }
        catch (RuntimeException e){
            model.addAttribute("httpSession", session);
            model.addAttribute("airport", new AirportDto());
            model.addAttribute("error", e.getMessage());
            return "create-airport";
        }

        model.addAttribute("httpSession", session);
        return "redirect:/airports";
    }

    @PostMapping("/airports/delete/{airportId}")
    public String deleteAirport(@PathVariable("airportId") String airportId, Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        try {
            airportService.delete(airportId);
        }
        catch (RuntimeException e){
            var airports = airportService.getAllAirports();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("airports", airports);
            return "airports";
        }
        model.addAttribute("httpSession", session);
        return "redirect:/airports";
    }

    @GetMapping("/airports/update/{airportId}")
    public String updateSelectedAirport(@PathVariable("airportId")String airportId, Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airport = airportService.getAirportById(airportId);
        model.addAttribute("httpSession", session);
        model.addAttribute("airport", airport);
        return "update-airport";
    }

    @PostMapping("/airports/update")
    public String updateAirport(@ModelAttribute("airportId")AirportDto airport, Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        try{
            airportService.update(airport.getId(), airport);
        }
        catch (RuntimeException e){
            var airports = airportService.getAllAirports();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("airports", airports);
            return "airports";
        }
        model.addAttribute("httpSession", session);
        return "redirect:/airports";
    }
}
