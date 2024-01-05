package hr.OSSAirline.mappers;

import hr.OSSAirline.dto.SeatDto;
import hr.OSSAirline.models.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatDto toDto(Seat seat);
    Seat toEntity(SeatDto seatDto);
}