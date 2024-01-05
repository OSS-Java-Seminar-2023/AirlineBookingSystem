package hr.OSSAirline.models;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;


@Data
@Entity(name = "ticket")
public class Ticket {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    @Column
    private Float ticketPrice;

}

