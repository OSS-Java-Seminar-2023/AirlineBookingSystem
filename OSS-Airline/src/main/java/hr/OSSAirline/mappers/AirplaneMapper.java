package hr.OSSAirline.mappers;

import hr.OSSAirline.models.Airplane;
import hr.OSSAirline.dto.AirplaneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirplaneMapper {

    AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);

    @Mapping(target = "id", source = "id")
    AirplaneDto toDto(Airplane airplane);

    @Mapping(target = "id", source = "id")
    Airplane toEntity(AirplaneDto airplaneDto);
}
