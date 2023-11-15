package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {

    public UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
}
