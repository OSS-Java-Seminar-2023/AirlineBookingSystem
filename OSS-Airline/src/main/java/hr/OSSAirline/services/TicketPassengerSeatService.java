package hr.OSSAirline.services;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.mappers.PassengerMapper;
import hr.OSSAirline.mappers.SeatMapper;
import hr.OSSAirline.mappers.TicketMapper;
import hr.OSSAirline.repositories.PassengerRepository;
import hr.OSSAirline.repositories.SeatRepository;
import hr.OSSAirline.repositories.TicketRepository;
import hr.OSSAirline.utils.ComparisonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TicketPassengerSeatService {
    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;
    private final TicketMapper ticketMapper;
    private final FlightMapper flightMapper;
    private final PassengerMapper passengerMapper;
    private final SeatMapper seatMapper;

    public List<TicketDto> proccessTicket(Map<String, PassengerDto> passenger_map) {
        List<TicketDto> tickets = new ArrayList<>();

        for (Map.Entry<String, PassengerDto> entry : passenger_map.entrySet()) {
            PassengerDto passengerDto = entry.getValue();
            PassengerDto existingPassenger = passengerMapper.toDto(passengerRepository.findByPIN(passengerDto.getPIN()));

            if (existingPassenger == null) {
                passengerRepository.save(passengerMapper.toEntity(passengerDto));
            } else {
                if(ComparisonUtil.areEqual(passengerDto,existingPassenger)) {
                    passengerDto = existingPassenger;
                }
                else {
                    throw new RuntimeException("Passenegr exists wrong data!");
                }
            }

            TicketDto ticketDto = new TicketDto();
            ticketDto.setPassenger(passengerDto);
            ticketDto.setSeat(seatMapper.toDto(seatRepository.findById(entry.getKey()).orElse(null)));
            ticketDto.setFlight(flightMapper.toDto(seatRepository.findById(entry.getKey()).orElse(null).getFlight()));
            ticketDto.setTicketPrice(seatRepository.findById(entry.getKey()).orElse(null).getSeatPrice()); // implement logic behind cheaper price for childern or teens
            tickets.add(ticketDto);
        }

        return tickets;
    }

}
