package hr.OSSAirline.services;

import hr.OSSAirline.repositories.AirplaneRepository;
import hr.OSSAirline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService {
    public final AirplaneRepository airplaneRepository;

    @Autowired
    public AirplaneService(AirplaneRepository airplaneRepository){
        this.airplaneRepository=airplaneRepository;
    }
}
