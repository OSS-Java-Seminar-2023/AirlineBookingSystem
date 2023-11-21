package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;


@Setter
@Getter
@Entity(name = "ticket")
public class Ticket {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private hr.OSSAirline.models.Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private hr.OSSAirline.models.Flight flight;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private hr.OSSAirline.models.Seat seat;
    @Column
    private Float ticketPrice;

}

