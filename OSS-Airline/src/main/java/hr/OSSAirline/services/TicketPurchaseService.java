package hr.OSSAirline.services;

import hr.OSSAirline.controllers.TicketPurchaseController;
import hr.OSSAirline.mappers.PurchaseMapper;
import hr.OSSAirline.models.*;
import hr.OSSAirline.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TicketPurchaseService {
    private final TicketRepository ticketRepository;
    private final PurchaseRepository purchaseRepository;
    //    private final PurchaseMapper purchaseMapper;
    public final FlightRepository flightRepository;
    public final PassengerRepository passengerRepository;
    public final SeatRepository seatRepository;
    private final UserRepository userRepository;



    public void makePurchase(TicketPurchaseController.TicketForm ticketForm, String userName) {
        var user = userRepository.findUserByUsername(userName);
        var flights = ticketForm.getFlight().stream().map(flightRepository::findById).toList();
        var passengers = ticketForm.getPassenger().stream().map(passengerRepository::findById).toList();
        var seats = ticketForm.getSeat().stream().map(seatRepository::findById).toList();
        var prices = ticketForm.getPrice().stream().toList();
        var ticket_list = new ArrayList<Ticket>();
        for (int i = 0; i < passengers.size(); i++) {
            var ticket = new Ticket();
            ticket.setFlight(flights.get(i).orElseThrow());
            ticket.setPassenger(passengers.get(i).orElseThrow());
            ticket.setSeat(seats.get(i).orElseThrow());
            ticket.setTicketPrice(prices.get(i));
            ticket_list.add(ticket);
        }
        var purchase = new Purchase();
        purchase.setUser(user.get());
        purchase.setTickets(ticket_list);
        purchaseRepository.save(purchase);
        ticket_list.stream()
                .forEach(ticket -> {
                    ticket.setPurchase(purchase);
                    ticketRepository.updatePurchaseField(ticket.getId(),purchase);
                });
    }
}
