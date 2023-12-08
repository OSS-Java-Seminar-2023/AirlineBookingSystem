package hr.OSSAirline.services;

import hr.OSSAirline.models.Airport;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;

    public List<Flight> getFlights(Airport from, Airport to, Date date){
        System.out.println(from.getName());
        System.out.println(to.getName());
        return flightRepository.searchFlightsByFromAndToAndDate(from,to,date);
    }

}
