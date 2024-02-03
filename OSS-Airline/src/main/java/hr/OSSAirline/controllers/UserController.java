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
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admin")
    public String showAdminPage(Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("httpSession",session);
        return "admin";
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
        session.setAttribute("isAdmin", userService.getUserByUsername(user.getUsername()).getIsAdmin());
        model.addAttribute("httpSession",session);
        return "redirect:/";
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

    @GetMapping("/users")
    public String listUsers(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var users = userService.getAllUsersNotAdmins();
        var admins = userService.getAllAdmins();
        model.addAttribute("users", users);
        model.addAttribute("admins", admins);
        model.addAttribute("httpSession",session);
        return "users";
    }

    @GetMapping("/users/create")
    public String createUser(Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        model.addAttribute("user", new UserDto());
        model.addAttribute("httpSession",session);
        return "create-user";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute UserDto user, @RequestParam String confirmPassword, Model model, HttpSession session) {
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        try {
            if (!user.getPassword().equals(confirmPassword)) {
                throw new RuntimeException("Password mismatch!");
            }
            userService.registerUser(user);
        } catch (RuntimeException | MessagingException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            model.addAttribute("httpSession",session);
            return "create-user";
        }
        return "redirect:/users";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam("userId")String userId, Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var loggedInUser = userService.getUserByUsername(session.getAttribute("userName").toString());
        try{
            userService.deleteUser(userId, loggedInUser);
        }
        catch (RuntimeException e){
            var users = userService.getAllUsers();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("users", users);
            model.addAttribute("httpSession",session);
            return "users";
        }
        return "redirect:/users";
    }

    @GetMapping("/users/update/{username}")
    public String updateSelectedUser(@PathVariable String username, Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        var user = userService.getUserByUsername(username);
        model.addAttribute("httpSession", session);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/users/update")
    public String updateUserToAdmin(@ModelAttribute("user")UserDto userDto, Model model, HttpSession session){
        var x = SecurityCheck.isUserAdminIfNotReturnToHome(session);
        if (x != null) return x;
        try{
            userService.updateUserToAdmin(userDto.getId(),userDto);
        }
        catch (RuntimeException e){
            var user = userService.getUserById(userDto.getId());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("httpSession", session);
            model.addAttribute("user", user);
            return "update-user";
        }
        model.addAttribute("httpSession",session);
        return "redirect:/users";
    }
}
