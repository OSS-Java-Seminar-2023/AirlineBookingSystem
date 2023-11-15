package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    public UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){

        this.userRepository=userRepository;
    }
}
