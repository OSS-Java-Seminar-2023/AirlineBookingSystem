package hr.OSSAirline.mappers;

import hr.OSSAirline.models.Flight;
import hr.OSSAirline.dto.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "from", source = "from")
    @Mapping(target = "to", source = "to")
    @Mapping(target = "airplane", source = "airplane")
    FlightDto toDto(Flight flight);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "from", source = "from")
    @Mapping(target = "to", source = "to")
    @Mapping(target = "airplane", source = "airplane")
    Flight toEntity(FlightDto flightDto);
}
