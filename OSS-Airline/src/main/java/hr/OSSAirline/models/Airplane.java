package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;


@Setter
@Getter
@Entity(name = "airplane")
public class Airplane {
    @Id
    @UuidGenerator
    private String id;

    @Column
    private String registration;
    @Column
    private String model;
    @Column
    private int firstSeats;
    @Column
    private int businessSeats;
    @Column
    private int economySeats;
}

