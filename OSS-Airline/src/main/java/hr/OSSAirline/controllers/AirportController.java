package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.AirplaneDto;
import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.services.AirportService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/airports")
    public String getAllAirports(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airports = airportService.getAllAirports();
        model.addAttribute("httpSession", session);
        model.addAttribute("airports", airports);
        return "airports";
    }

    @GetMapping("/airport/create")
    public String createAirport(Model model,HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("httpSession", session);
        model.addAttribute("airport", new AirportDto());
        return "create-airport";
    }

    @PostMapping("/airport/create")
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

    @PostMapping("/airport/delete")
    public String deleteAirport(@RequestParam("airportId") String airportId, Model model, HttpSession session){
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

    @PostMapping("/airports/update")
    public String updateSelectedAirport(@RequestParam("airportId")String airportId, Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airport = airportService.getAirportById(airportId);
        model.addAttribute("httpSession", session);
        model.addAttribute("airport", airport);
        return "update-airport";
    }

    @PostMapping("/airport/update")
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
