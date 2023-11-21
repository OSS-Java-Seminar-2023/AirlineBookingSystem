package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
public class seat {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private hr.OSSAirline.models.flight flight;
    @Column
    private Float seatPrice;
    @Column
    private String seatClass;

}

