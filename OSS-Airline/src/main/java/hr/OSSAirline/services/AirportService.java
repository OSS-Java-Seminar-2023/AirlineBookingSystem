package hr.OSSAirline.services;

import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.mappers.AirportMapper;
import hr.OSSAirline.models.Airport;
import hr.OSSAirline.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    public List<AirportDto> getAllAirports(){
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airportMapper::toDto)
                .collect(Collectors.toList());
    }
    public AirportDto getAirportByName(String name) {
        return airportRepository.findByName(name)
                .map(airportMapper::toDto)
                .orElse(null);
    }
}
