package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity(name = "airport")
public class Airport {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String name;
    @Column
    private String IATA;

}

