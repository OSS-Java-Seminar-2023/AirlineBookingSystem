package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.UserDto;
import hr.OSSAirline.repositories.PurchaseRepository;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    @GetMapping("/purchase")
    public String seePurchase(Model model, HttpSession session){
        var x = SecurityCheck.isUserLoggedInReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("httpSession",session);
        return "purchase";
    }
}
