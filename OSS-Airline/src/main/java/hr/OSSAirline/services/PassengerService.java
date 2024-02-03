package hr.OSSAirline.services;

import hr.OSSAirline.repositories.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerService {
    public final PassengerRepository passengerRepository;

}
