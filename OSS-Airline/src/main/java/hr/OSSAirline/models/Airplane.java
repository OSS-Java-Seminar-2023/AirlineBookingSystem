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
    @Column(name="first_seats")
    private Integer firstSeats;
    @Column(name="business_seats")
    private Integer businessSeats;
    @Column(name="economy_seats")
    private Integer economySeats;
}

