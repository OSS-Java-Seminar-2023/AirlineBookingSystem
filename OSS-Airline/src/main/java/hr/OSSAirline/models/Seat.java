package hr.OSSAirline.models;

import hr.OSSAirline.models.Flight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Setter
@Getter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column
    private Float seatPrice;
    @Column
    private String seatClass;

}

