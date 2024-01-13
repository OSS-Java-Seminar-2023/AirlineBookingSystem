package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.AirplaneDto;
import hr.OSSAirline.services.AirplaneService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AirplaneController {

    private final AirplaneService airplaneService;

    @GetMapping("/airplanes")
    public String getAllAirplanes(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airplanes = airplaneService.getAllAirplanes();
        model.addAttribute("httpSession", session);
        model.addAttribute("airplanes", airplanes);
        return "airplanes";
    }

    @PostMapping("/airplane/delete")
    public String deleteAirplane(@RequestParam("airplaneId") String airplaneId, Model model, HttpSession session) {
        try {
            airplaneService.deleteAirplane(airplaneId);
        }
        catch (RuntimeException e){
            var airplanes = airplaneService.getAllAirplanes();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("airplanes", airplanes);
            return "airplanes";
        }
        model.addAttribute("httpSession", session);
        return "redirect:/airplanes";
    }

    @PostMapping("/airplane/update")
    public String updateAirplane(@ModelAttribute("airplane") AirplaneDto airplane, Model model, HttpSession session) {
        try {
            airplaneService.updateAirplane(airplane.getId(), airplane);
        }
        catch (RuntimeException e){
            var airplanes = airplaneService.getAllAirplanes();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("airplanes", airplanes);
            return "airplanes";
        }
        model.addAttribute("httpSession", session);
        return "redirect:/airplanes";
    }

    @PostMapping("/airplanes/update")
    public String updateSelectedAirplane(@RequestParam("airplaneId") String airplaneId, Model model, HttpSession session) {
        var airplane = airplaneService.getAirplaneById(airplaneId);
        model.addAttribute("httpSession", session);
        model.addAttribute("airplane", airplane);

        return "update-airplane";
    }

    @PostMapping("/airplane/info")
    public String airplaneInfo(@RequestParam("airplaneId") String airplaneId, Model model, HttpSession session) {
        var airplane = airplaneService.getAirplaneById(airplaneId);
        model.addAttribute("httpSession", session);
        model.addAttribute("airplane", airplane);

        return "airplane"; // Redirect to the airplane list page
    }

    @GetMapping("/airplane/create")
    public String airplaneCreate(Model model, HttpSession session) {
        model.addAttribute("httpSession", session);
        model.addAttribute("airplane", new AirplaneDto());

        return "create-airplane";
    }

    @PostMapping("/airplane/create")
    public String airplaneCreate(@ModelAttribute("airplane") AirplaneDto airplane, Model model, HttpSession session) {
        airplaneService.saveAirplane(airplane);

        model.addAttribute("httpSession", session);

        return "redirect:/airplanes";
    }
}
