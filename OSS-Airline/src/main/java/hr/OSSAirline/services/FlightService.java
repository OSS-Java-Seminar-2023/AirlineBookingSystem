package hr.OSSAirline.services;

import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final SeatService seatService;

    public List<FlightDto> getAllFlights(){
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .toList();

    }

    public void createFlight(FlightDto flightDto, Float firstClassPrice, Float businessClassPrice, Float economyClassPrice){
        var flight = flightRepository.save(flightMapper.toEntity(flightDto));
        seatService.generateSeatsForFlight(flight,firstClassPrice,businessClassPrice,economyClassPrice);
    }
}
