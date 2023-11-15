package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

