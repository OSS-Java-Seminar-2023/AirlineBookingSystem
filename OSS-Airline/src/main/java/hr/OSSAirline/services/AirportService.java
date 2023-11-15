package hr.OSSAirline.services;

import hr.OSSAirline.repositories.AirplaneRepository;
import hr.OSSAirline.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    public final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository){
        this.airportRepository=airportRepository;
    }
}
