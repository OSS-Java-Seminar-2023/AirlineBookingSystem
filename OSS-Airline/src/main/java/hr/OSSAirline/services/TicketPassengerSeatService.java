package hr.OSSAirline.services;

import hr.OSSAirline.dto.PassengerDto;
import hr.OSSAirline.dto.TicketDto;
import hr.OSSAirline.mappers.FlightMapper;
import hr.OSSAirline.mappers.PassengerMapper;
import hr.OSSAirline.mappers.SeatMapper;
import hr.OSSAirline.mappers.TicketMapper;
import hr.OSSAirline.repositories.PassengerRepository;
import hr.OSSAirline.repositories.SeatRepository;
import hr.OSSAirline.utils.ComparisonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TicketPassengerSeatService {
    private static final float BABY_DISCOUNT_PERCENTAGE = 0.6f;
    private static final float CHILD_DISCOUNT_PERCENTAGE = 0.75f;
    private static final float ELDER_DISCOUNT_PERCENTAGE = 0.85f;
    private static final int BABY_AGE_THRESHOLD = 2;
    private static final int CHILD_AGE_THRESHOLD = 14;
    private static final int ELDER_AGE_THRESHOLD = 65;



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
                if (ComparisonUtil.areEqual(passengerDto, existingPassenger)) {
                    passengerDto = existingPassenger;
                } else {
                    throw new RuntimeException("Passenger exists wrong data!");
                }
            }

            TicketDto ticketDto = new TicketDto();
            ticketDto.setPassenger(passengerDto);
            ticketDto.setSeat(seatMapper.toDto(seatRepository.findById(entry.getKey()).orElse(null)));
            ticketDto.setFlight(flightMapper.toDto(seatRepository.findById(entry.getKey()).orElse(null).getFlight()));
            var ticketPrice = calculateDiscountedPrice(passengerDto.getDOB(), seatRepository.findById(entry.getKey()).orElse(null).getSeatPrice());
            ticketDto.setTicketPrice(ticketPrice);
            tickets.add(ticketDto);
        }

        return tickets;
    }

    private Float calculateDiscountedPrice(Date DOB, Float basePrice) {
        var age = calculateAge(DOB);
        if (age < BABY_AGE_THRESHOLD) {
            return basePrice * BABY_DISCOUNT_PERCENTAGE;
        } else if (BABY_AGE_THRESHOLD < age && age < CHILD_AGE_THRESHOLD) {
            return basePrice * CHILD_DISCOUNT_PERCENTAGE;
        } else if (age >= ELDER_AGE_THRESHOLD) {
            return basePrice * ELDER_DISCOUNT_PERCENTAGE;
        } else {
            return basePrice;
        }
    }

    private int calculateAge(Date DOB) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(DOB);
        Calendar currentDate = Calendar.getInstance();
        int years = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            years--;
        }
        return years;
    }
}
