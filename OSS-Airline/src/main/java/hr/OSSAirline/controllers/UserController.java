package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.UserDto;
import hr.OSSAirline.mappers.UserMapper;
import hr.OSSAirline.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user_registration";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute UserDto user, @RequestParam String confirmPassword, Model model) {
        try {
            if (!user.getPassword().equals(confirmPassword)) {
                throw new RuntimeException("Password mismatch!");
            }
            userService.registerUser(user);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            return "user_registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new UserDto());
        return "user_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto user, HttpSession session, Model model) {
        try {
            userService.authenticate(user.getUsername(), user.getPassword());
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("loginRequest", user);
            return "user_login";
        }
        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getUsername());
        return "redirect:/";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
