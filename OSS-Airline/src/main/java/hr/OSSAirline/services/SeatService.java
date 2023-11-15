package hr.OSSAirline.services;

import hr.OSSAirline.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    public final SeatRepository seatRepository;
    @Autowired
    public SeatService(SeatRepository seatRepository){
        this.seatRepository=seatRepository;
    }
}
