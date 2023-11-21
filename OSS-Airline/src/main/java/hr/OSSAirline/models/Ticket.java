package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;


@Setter
@Getter
@Entity
public class ticket {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private hr.OSSAirline.models.passenger passenger;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private hr.OSSAirline.models.flight flight;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private hr.OSSAirline.models.seat seat;
    @Column
    private Float ticketPrice;

}

