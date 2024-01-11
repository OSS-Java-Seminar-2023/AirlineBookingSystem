package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.UserDto;
import hr.OSSAirline.exceptions.PasswordException;
import hr.OSSAirline.mappers.UserMapper;
import hr.OSSAirline.services.UserService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static hr.OSSAirline.utils.SecurityCheck.isUserLoggedInReturnToHome;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpSession session) {
        var x = SecurityCheck.isUserLoggedInReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("user", new UserDto());
        model.addAttribute("httpSession",session);
        return "user_registration";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute UserDto user, @RequestParam String confirmPassword, Model model, HttpSession session) {
        try {
            if (!user.getPassword().equals(confirmPassword)) {
                throw new RuntimeException("Password mismatch!");
            }
            userService.registerUser(user);
        } catch (RuntimeException | MessagingException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            model.addAttribute("httpSession",session);
            return "user_registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        var x = SecurityCheck.isUserLoggedInReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("loginRequest", new UserDto());
        model.addAttribute("httpSession",session);
        return "user_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto user, HttpSession session, Model model) {
        try {
            userService.authenticate(user.getUsername(), user.getPassword());
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession",session);
            model.addAttribute("loginRequest", user);
            return "user_login";
        }
        session.setAttribute("userName", user.getUsername());
        model.addAttribute("httpSession",session);
        return "redirect:/";
    }

    @GetMapping("/users")
    public String listUsers(Model model, HttpSession session) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("httpSession",session);
        return "userlist";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        model.addAttribute("httpSession",session);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/info")
    public String info(HttpSession session, Model model) {
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if(x != null) return x;
        var user = userService.getUserByUsername(session.getAttribute("userName").toString());
        model.addAttribute("user", user);
        model.addAttribute("httpSession",session);

        return "user_info";
    }

    @GetMapping("/changePassword")
    public String changePassword(HttpSession session, Model model){
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if(x != null) return x;

        model.addAttribute("httpSession",session);

        return "change-pass";
    }

    @PostMapping("/changePassword")
    public String changePasswordPost(@RequestParam String oldPassword, @RequestParam String newPassword,@RequestParam String passConfirm, HttpSession session, Model model){
        var x = SecurityCheck.isUserNotLoggedInReturnToLogin(session);
        if(x != null) return x;

        try {
            userService.changePassword(session.getAttribute("userName").toString(), newPassword, oldPassword, passConfirm);
        }
        catch (PasswordException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession",session);
            var user = userService.getUserByUsername(session.getAttribute("userName").toString());
            model.addAttribute("user", user);
            return "user_info";
        }

        model.addAttribute("info", "Password successfully changed!");
        model.addAttribute("httpSession",session);
        var user = userService.getUserByUsername(session.getAttribute("userName").toString());
        model.addAttribute("user", user);

        return "user_info";
    }
}
