package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Setter
@Getter
@Entity(name = "passenger")
public class Passenger {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String mail;
    @Column
    private String PIN;
    @Column
    @DateTimeFormat
    private java.sql.Date DOB;
}

