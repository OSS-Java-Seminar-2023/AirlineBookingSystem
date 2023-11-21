package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity(name = "seat")
public class Seat {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column
    private Float seatPrice;
    @Column
    private String seatClass;

}

