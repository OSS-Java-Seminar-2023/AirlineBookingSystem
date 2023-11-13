package hr.OSSAirline.airport;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Airport {
    @Id
    @Column
    private String id = UUID.randomUUID().toString();
    @Column
    private String airportName;
    @Column
    private String IATA;

}

