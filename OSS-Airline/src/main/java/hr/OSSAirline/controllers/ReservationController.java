package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.ReservationRepository;
import hr.OSSAirline.repositories.UserRepository;
import hr.OSSAirline.services.ReservationService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    private final UserRepository userRepository;

    @GetMapping("/reservations")
    public String seeReservations(Model model, HttpSession session){
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if (x != null) return x;

        var userName = session.getAttribute("userName").toString();
        var user = userRepository.findUserByUsername(userName);
        if(user.isEmpty()){
            return "user_login";
        }
        var reservations = reservationService.getAllUserReservations(user.get());
        model.addAttribute("httpSession",session);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }
}
