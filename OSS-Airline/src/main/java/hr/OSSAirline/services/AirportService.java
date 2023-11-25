package hr.OSSAirline.services;

import hr.OSSAirline.models.Airport;
import hr.OSSAirline.repositories.AirplaneRepository;
import hr.OSSAirline.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    public final AirportRepository airportRepository;

    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }
    public Airport getAirportByName(String name) {
        Optional<Airport> optionalAirport = airportRepository.findByName(name);
        return optionalAirport.orElse(null);
    }
}
