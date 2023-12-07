package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.criteria.CriteriaBuilder;


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
    private Integer firstSeats;
    @Column
    private Integer businessSeats;
    @Column
    private Integer economySeats;
}

