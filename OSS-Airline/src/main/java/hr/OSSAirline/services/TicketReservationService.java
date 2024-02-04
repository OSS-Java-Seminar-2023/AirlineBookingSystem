package hr.OSSAirline.services;


import hr.OSSAirline.models.*;
import hr.OSSAirline.repositories.*;
import hr.OSSAirline.utils.DateUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        var totalPrice = prices.stream().mapToDouble(Float::doubleValue).sum();
        var ticket_list = new ArrayList<Ticket>();
        for (int i = 0; i < passengers.size(); i++) {
            var ticket = new Ticket();
            ticket.setFlight(flights.get(i).orElseThrow());
            ticket.setPassenger(passengers.get(i).orElseThrow());
            ticket.setSeat(seats.get(i).orElseThrow());
            ticket.setTicketPrice(prices.get(i));
            ticket_list.add(ticket);
        }
        var reservation = new Reservation();
        reservation.setUser(user.get());
        reservation.setTickets(ticket_list);
        reservation.setPaymentDate(DateUtility.getCurrentDate());
        reservation.setPaymentInfo("Your total price is:" + totalPrice);
        reservationRepository.save(reservation);
        ticket_list.forEach(ticket -> {
                    ticket.setReservation(reservation);
                    ticketRepository.updateReservationField(ticket.getId(),reservation);
                });
    }
}
