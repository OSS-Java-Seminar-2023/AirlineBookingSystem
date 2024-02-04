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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    private final UserRepository userRepository;

    @GetMapping("/reservations-all")
    public String getAllReservations(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var reservations = reservationService.getAllReservations();
        model.addAttribute("httpSession", session);
        model.addAttribute("reservations", reservations);
        return "reservations-all";
    }

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

    @PostMapping("/reservations/details")
    public String detailedReservation(@RequestParam String reservationId, Model model, HttpSession session){
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if (x != null) return x;

        var reservation = reservationService.getReservationById(reservationId);
        model.addAttribute("httpSession",session);
        model.addAttribute("reservations", reservation);
        return "reservation-details";
    }
}
