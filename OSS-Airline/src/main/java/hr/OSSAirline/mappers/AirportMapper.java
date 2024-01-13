package hr.OSSAirline.mappers;

import hr.OSSAirline.models.Airport;
import hr.OSSAirline.dto.AirportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(target = "id", source = "id")
    AirportDto toDto(Airport airport);

    @Mapping(target = "id", source = "id")
    Airport toEntity(AirportDto airportDto);
}
