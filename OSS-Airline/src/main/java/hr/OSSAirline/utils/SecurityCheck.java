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
    public static String isUserNotLoggedInReturnToLogin(HttpSession session) {
        var userName= session.getAttribute("userName");
        if(userName==null){
            throw new RuntimeException("Login required!");
        }
        return null;
    }
}
