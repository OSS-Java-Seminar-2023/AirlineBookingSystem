package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AirplaneController {
    private final AirplaneRepository airplaneRepositoryrepository;

    @Autowired
    public AirplaneController(AirplaneRepository airplaneRepositoryrepository){
        this.airplaneRepositoryrepository=airplaneRepositoryrepository;
    }
}
