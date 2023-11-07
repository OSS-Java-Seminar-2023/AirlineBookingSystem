package hr.OSSAirline.seat;

import hr.OSSAirline.flight.Flight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;


@Setter
@Getter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    private Float seatPrice;
    private String seatClass;

}

