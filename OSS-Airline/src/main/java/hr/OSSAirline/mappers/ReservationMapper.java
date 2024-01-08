package hr.OSSAirline.mappers;

import hr.OSSAirline.dto.ReservationDto;
import hr.OSSAirline.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "tickets", target = "tickets")
    @Mapping(source = "paymentInfo", target = "paymentInfo")
    @Mapping(source = "paymentDate", target = "paymentDate")
    ReservationDto toDto(Reservation entity);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "tickets", target = "tickets")
    @Mapping(source = "paymentInfo", target = "paymentInfo")
    @Mapping(source = "paymentDate", target = "paymentDate")
    Reservation toEntity(ReservationDto dto);
}