package hr.OSSAirline.mappers;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.models.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    PassengerDto toDto(Passenger passenger);
    Passenger toEntity(PassengerDto passengerDto);
}
