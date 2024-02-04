package hr.OSSAirline.services;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.mappers.PassengerMapper;
import hr.OSSAirline.repositories.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    public List<PassengerDto> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(passengerMapper::toDto)
                .toList();
    }
}
