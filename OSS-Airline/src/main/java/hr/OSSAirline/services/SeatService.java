package hr.OSSAirline.services;

import hr.OSSAirline.repositories.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
    public final SeatRepository seatRepository;
}
