package hr.OSSAirline.flight;

import hr.OSSAirline.airplane.Airplane;
import hr.OSSAirline.airport.Airport;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Setter
@Getter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name = "from_id")
    private Airport from;
    @ManyToOne
    @JoinColumn(name = "to_id")
    private Airport to;
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
    @Column
    private Date date;
    @Column
    private Time time;
    @Column
    private String gate;
    @Column
    private Time duration;
}

