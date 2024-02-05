package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.AirplaneDto;
import hr.OSSAirline.services.AirplaneService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/airplanes/delete/{airplaneId}")
    public String deleteAirplane(@PathVariable String airplaneId, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
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

    @PostMapping("/airplanes/update")
    public String updateAirplane(@ModelAttribute("airplane") AirplaneDto airplane, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
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

    @GetMapping("/airplanes/update/{airplaneId}")
    public String updateSelectedAirplane(@PathVariable("airplaneId") String airplaneId, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airplane = airplaneService.getAirplaneById(airplaneId);
        model.addAttribute("httpSession", session);
        model.addAttribute("airplane", airplane);

        return "update-airplane";
    }

    @GetMapping("/airplanes/info/{airplaneId}")
    public String airplaneInfo(@PathVariable("airplaneId") String airplaneId, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var airplane = airplaneService.getAirplaneById(airplaneId);
        model.addAttribute("httpSession", session);
        model.addAttribute("airplane", airplane);

        return "airplane";
    }

    @GetMapping("/airplanes/create")
    public String airplaneCreate(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("httpSession", session);
        model.addAttribute("airplane", new AirplaneDto());
        model.addAttribute("models", airplaneService.getAllAirplanes());

        return "create-airplane";
    }

    @PostMapping("/airplanes/create")
    public String airplaneCreate(@ModelAttribute("airplane") AirplaneDto airplane, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        airplaneService.saveAirplane(airplane);

        model.addAttribute("httpSession", session);

        return "redirect:/airplanes";
    }
}
