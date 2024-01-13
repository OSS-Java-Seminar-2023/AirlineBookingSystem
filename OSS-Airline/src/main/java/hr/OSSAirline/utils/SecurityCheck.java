package hr.OSSAirline.utils;

import hr.OSSAirline.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecurityCheck {

    public final UserRepository userRepository;
    public static String isUserLoggedInReturnToHome(HttpSession session) {
        var userName= session.getAttribute("userName");
        if(userName!=null){
            return "redirect:/";
        }
        return null;
    }
    public static String isUserNotLoggedInReturnToLogin(HttpSession session) {
        var userName= session.getAttribute("userName");
        if(userName==null){
            return "redirect:/login";
        }
        return null;
    }

    public static String isUserAdminIfNotReturnToHome(HttpSession session) {
        var userName = session.getAttribute("userName");
        if(userName==null){
            return "redirect:/login";
        }
        var isAdmin = session.getAttribute("isAdmin");
        if(isAdmin.toString().equals("true")) {
            return null;
        }
        return "redirect:/";
    }
}
