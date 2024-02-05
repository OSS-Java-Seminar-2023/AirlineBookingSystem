package hr.OSSAirline.services;


import hr.OSSAirline.models.*;
import hr.OSSAirline.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketReservationService {
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
    public final FlightRepository flightRepository;
    public final PassengerRepository passengerRepository;
    public final SeatRepository seatRepository;
    private final UserRepository userRepository;


    public void makeReservation(TicketForm ticketForm, String userName) {
        var user = userRepository.findUserByUsername(userName);
        var flights = ticketForm.getFlight().stream().map(flightRepository::findById).toList();
        var passengers = ticketForm.getPassenger().stream().map(passengerRepository::findById).toList();
        var seats = ticketForm.getSeat().stream().map(seatRepository::findById).toList();
        var prices = ticketForm.getPrice().stream().toList();
        var ticket_list = new ArrayList<Ticket>();
        generateTickets(passengers, flights, seats, prices, ticket_list);
        var reservation = new Reservation();
        reservation.setUser(user.orElseThrow());
        reservation.setTickets(ticket_list);
        reservationRepository.save(reservation);
        ticket_list.forEach(ticket -> {
                    ticket.setReservation(reservation);
                    ticketRepository.updateReservationField(ticket.getId(),reservation);
                });
    }

    private static void generateTickets(List<Optional<Passenger>> passengers, List<Optional<Flight>> flights, List<Optional<Seat>> seats, List<Float> prices, ArrayList<Ticket> ticket_list) {
        int index = 0;
        for (var passenger : passengers) {
            var ticket = new Ticket();
            ticket.setFlight(flights.get(index).orElseThrow());
            ticket.setPassenger(passenger.orElseThrow());
            ticket.setSeat(seats.get(index).orElseThrow());
            ticket.setTicketPrice(prices.get(index));
            ticket_list.add(ticket);
            index++;
        }
    }
}
