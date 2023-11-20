package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
public class Airport {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String airportName;
    @Column
    private String IATA;

}

