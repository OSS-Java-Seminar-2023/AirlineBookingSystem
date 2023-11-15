package hr.OSSAirline.services;

import hr.OSSAirline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    public final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository){
        this.passengerRepository=passengerRepository;
    }
}
