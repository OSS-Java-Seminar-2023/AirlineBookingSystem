package hr.OSSAirline.utils;

import jakarta.servlet.http.HttpSession;

public class SecurityCheck {
    public static String isUserLoggedInReturnToHome(HttpSession session) {
        var userName= session.getAttribute("userName");
        if(userName!=null){
            return "redirect:/";
        }
        return null;
    }
}
