package hr.OSSAirline.controllers;

import hr.OSSAirline.models.user;
import hr.OSSAirline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;


//    @GetMapping("/hello")
//    public String sayhello() {
//        return "hello";
//    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new user());
        return "user_registration";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute user user) {
        // Save the user to the database
        userService.registerUser(user);

        // Redirect to a success page or login page
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
            model.addAttribute("loginRequest", new user());
            return "user_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute user user) {
        System.out.println("login request " + user);
        return userService.authenticate(user.getEmail(), user.getPassword()) ? "user_login" : "redirect:/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        // Retrieve the list of users from the service
        List<user> users = userService.getAllUsers();

        // Add the list of users to the model
        model.addAttribute("users", users);

        // Return the Thymeleaf template name
        return "userlist";
    }
}
