package hr.OSSAirline.services;

import hr.OSSAirline.models.Flight;
import hr.OSSAirline.models.Seat;
import hr.OSSAirline.repositories.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatService {
    public final SeatRepository seatRepository;

    @Transactional
    public void generateSeatsForFlight(Flight flight, Float firstClassPrice, Float businessClassPrice, Float economyClassPrice) {
        var firstClassSeats = flight.getAirplane().getFirstSeats();
        var businessClassSeats = flight.getAirplane().getBusinessSeats();
        var economyClassSeats = flight.getAirplane().getEconomySeats();

        generateSeats(flight, firstClassSeats, "First Class", firstClassPrice);
        generateSeats(flight, businessClassSeats, "Business Class", businessClassPrice);
        generateSeats(flight, economyClassSeats, "Economy Class", economyClassPrice);
    }

    private void generateSeats(Flight flight, int numberOfSeats, String seatClass, float seatPrice) {
        for (int i = 1; i <= numberOfSeats; i++) {
            var seat = new Seat();
            seat.setId(UUID.randomUUID().toString());
            seat.setFlight(flight);
            seat.setSeatNumber(generateSeatNumber(seatClass, i));
            seat.setSeatPrice(seatPrice);
            seat.setSeatClass(seatClass);
            seatRepository.save(seat);
        }
    }

    private String generateSeatNumber(String seatClass, int seatNumber) {
        return seatClass.substring(0, 1) + seatNumber;
    }
}
