package hr.OSSAirline.flight;

import hr.OSSAirline.airplane.Airplane;
import hr.OSSAirline.airport.Airport;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.sql.Date;

@Setter
@Getter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Date date;
    private Time time;
    private String gate;
    private Time duration;
}

