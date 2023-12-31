package hr.OSSAirline.services;

import hr.OSSAirline.controllers.TicketPurchaseController;
import hr.OSSAirline.mappers.PurchaseMapper;
import hr.OSSAirline.models.Flight;
import hr.OSSAirline.models.Passenger;
import hr.OSSAirline.models.Seat;
import hr.OSSAirline.models.Ticket;
import hr.OSSAirline.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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



    public void makePurchase(TicketPurchaseController.TicketForm ticketForm) {
//        var tickets = ticketForm.getTickets();
        var flights = ticketForm.getFlight().stream().map(flightId -> flightRepository.findById(flightId)).collect(Collectors.toList());
        var passengers = ticketForm.getPassenger().stream().map(passengerId -> passengerRepository.findById(passengerId)).collect(Collectors.toList());
        var seats = ticketForm.getSeat().stream().map(seatId -> seatRepository.findById(seatId)).collect(Collectors.toList());

        System.out.println("check");
    }
}
