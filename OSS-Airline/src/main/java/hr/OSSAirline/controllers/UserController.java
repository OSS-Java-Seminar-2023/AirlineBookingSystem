package hr.OSSAirline.controllers;

import hr.OSSAirline.models.User;
import hr.OSSAirline.repositories.UserRepository;
import hr.OSSAirline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService=userService;
    }


    @GetMapping("/hello")
    public String sayhello() {
        return "hello";
    }

//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "user_registration";
//    }
//
//    @PostMapping("/register")
//    public String processRegistration(@ModelAttribute User user) {
//        // Save the user to the database
//        userService.registerUser(user);
//        // Redirect to a success page or login page
//        return "redirect:/login";
//    }
}
