package hr.OSSAirline.models;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity(name = "seat")
public class Seat {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column
    private Float seatPrice;
    @Column
    private String seatClass;
    @Column
    private String seatNumber;
}

