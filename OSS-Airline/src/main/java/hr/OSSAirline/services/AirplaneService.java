package hr.OSSAirline.services;

import hr.OSSAirline.dto.AirplaneDto;
import hr.OSSAirline.mappers.AirplaneMapper;
import hr.OSSAirline.models.Airplane;
import hr.OSSAirline.repositories.AirplaneRepository;
import hr.OSSAirline.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirplaneService {
    public final AirplaneRepository airplaneRepository;
    public final AirplaneMapper airplaneMapper;
    public final FlightRepository flightRepository;

    public void saveAirplane(AirplaneDto airplaneDto) {
        airplaneRepository.save(airplaneMapper.toEntity(airplaneDto));
    }

    public List<AirplaneDto> getAllAirplanes() {
        return airplaneRepository.findAll().stream()
                .map(airplaneMapper::toDto)
                .toList();
    }

    public AirplaneDto findAirplaneById(String id) throws Exception {
        var airplane = airplaneRepository.findById(id);
        if(airplane.isPresent()){
            return airplaneMapper.toDto(airplane.get());
        }
        throw new Exception("Error");
    }

    public AirplaneDto getAirplaneById(String id){
        return airplaneMapper.toDto(airplaneRepository.getReferenceById(id));
    }

    public Airplane updateAirplane(String id, AirplaneDto updatedAirplane) {
        if (airplaneRepository.existsById(id) && !flightRepository.existsByAirplane_Id(id)) {
            updatedAirplane.setId(id);
            return airplaneRepository.save(airplaneMapper.toEntity(updatedAirplane));
        } else {
            System.out.println("NOT DELETED");
            return null;
        }
    }
    public void deleteAirplane(String id) {
        if(airplaneRepository.existsById(id) && !flightRepository.existsByAirplane_Id(id)){
        airplaneRepository.deleteById(id);
        }
        else {
            System.out.println("NOT DELETED");
        }
    }
}
