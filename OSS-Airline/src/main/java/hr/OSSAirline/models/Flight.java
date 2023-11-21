package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import org.hibernate.annotations.UuidGenerator;


@Setter
@Getter
@Entity
public class flight {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name = "from_id")
    private airport from;
    @ManyToOne
    @JoinColumn(name = "to_id")
    private airport to;
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private hr.OSSAirline.models.airplane airplane;
    @Column
    private java.sql.Date date;
    @Column
    private java.sql.Time time;
    @Column
    private String gate;
    @Column
    private java.sql.Time duration;
}

