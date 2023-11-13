package hr.OSSAirline.passenger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Passenger {
    @Id
    @Column
    private String id = UUID.randomUUID().toString();
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
    private Date DOB;
}

