package hr.OSSAirline.services;

import hr.OSSAirline.dto.AirportDto;
import hr.OSSAirline.mappers.AirportMapper;
import hr.OSSAirline.models.Airport;
import hr.OSSAirline.repositories.AirportRepository;
import hr.OSSAirline.repositories.FlightRepository;
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
    private final FlightRepository flightRepository;

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

    public AirportDto getAirportById(String id){
        return airportMapper.toDto(airportRepository.getReferenceById(id));
    }

    public void save(AirportDto airport){
        if(airport.getIATA().length()>3){ throw new RuntimeException("Airport IATA is longer than 3 characters.");}
        if(!airportRepository.existsByIATA(airport.getIATA())){
            airportRepository.save(airportMapper.toEntity(airport));
        }
        else{
            throw new RuntimeException("Airport IATA already exists.");
        }
    }

    public void delete(String id){
        if(airportRepository.existsById(id) && !flightRepository.existsByTo_Id(id) && !flightRepository.existsByFrom_Id(id)){
            airportRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Can not delete airport while flights to or from it exist.");
        }
    }

    public Airport update(String id, AirportDto airportDto){
        if(airportRepository.existsById(id) && !flightRepository.existsByTo_Id(id) && !flightRepository.existsByFrom_Id(id)){
            airportDto.setId(id);
            return airportRepository.save(airportMapper.toEntity(airportDto));
        } else {
            throw new RuntimeException("Can not update airport while flights to or from it exist.");
        }
    }
}
