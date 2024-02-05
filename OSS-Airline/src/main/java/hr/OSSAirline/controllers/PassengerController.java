package hr.OSSAirline.controllers;

import hr.OSSAirline.services.PassengerService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PassengerController {

    private  final PassengerService passengerService;
    @GetMapping("/passengers")
    public String getAllPassengers(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var passengers = passengerService.getAllPassengers();
        model.addAttribute("httpSession", session);
        model.addAttribute("passengers", passengers);
        return "passengers";
    }
}
