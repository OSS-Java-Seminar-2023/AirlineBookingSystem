package hr.OSSAirline.ticket;

import hr.OSSAirline.flight.Flight;
import hr.OSSAirline.passenger.Passenger;
import hr.OSSAirline.seat.Seat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @Column
    private Float ticketPrice;

}

