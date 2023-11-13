package hr.OSSAirline.seat;

import hr.OSSAirline.flight.Flight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Setter
@Getter
@Entity
public class Seat {
    @Id
    @Column
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column
    private Float seatPrice;
    @Column
    private String seatClass;

}

