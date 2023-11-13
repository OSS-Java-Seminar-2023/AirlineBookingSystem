package hr.OSSAirline.airplane;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Airplane {
    @Id
    @Column
    private String id = UUID.randomUUID().toString();

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

