package hr.OSSAirline.controllers;

import hr.OSSAirline.models.User;
import hr.OSSAirline.services.UserService;
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

    public final UserService userService;


//    @GetMapping("/hello")
//    public String sayhello() {
//        return "hello";
//    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user_registration";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute User user, @RequestParam String confirmPassword) {
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
            model.addAttribute("loginRequest", new User());
            return "user_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        if(!userService.usernameTaken(user.getUsername())){
            return "redirect:/login?error=Wrong username or password!";
        }
        return userService.authenticate(user.getUsername(), user.getPassword()) ? "redirect:/users" : "redirect:/login?error=Wrong username or password!";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        // Retrieve the list of users from the service
        List<User> users = userService.getAllUsers();

        // Add the list of users to the model
        model.addAttribute("users", users);

        // Return the Thymeleaf template name
        return "userlist";
    }
}
