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
    public String processRegistration(@ModelAttribute UserDto user, @RequestParam String confirmPassword) {
        if(userService.usernameTaken(user.getUsername())){
            return "redirect:/register?error=Username taken!";
        }
        if(userService.emailTaken(user.getEmail())){
            return "redirect:/register?error=Email is already in use!";
        }
        if(!user.getPassword().equals(confirmPassword)){
            return "redirect:/register?error=Password mismatch!";
        }
        // Save the user to the database
        userService.registerUser(user);

        // Redirect to a success page or login page
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
            model.addAttribute("loginRequest", new UserDto());
            return "user_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto user, HttpSession session) {
        if(!userService.usernameTaken(user.getUsername())){
            return "redirect:/login?error=Wrong username or password!";
        }
        if(userService.authenticate(user.getUsername(), user.getPassword())){
            session.setAttribute("userId",user.getId());
            session.setAttribute("userName",user.getUsername());
            return "redirect:/";}
        return "redirect:/login?error=Wrong username or password!";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
