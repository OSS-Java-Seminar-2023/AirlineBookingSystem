package hr.OSSAirline.mappers;

import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.models.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "passenger", source = "passenger")
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "seat", source = "seat")
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "passenger", source = "passenger")
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "seat", source = "seat")
    Ticket toEntity(TicketDto ticketDto);
}

