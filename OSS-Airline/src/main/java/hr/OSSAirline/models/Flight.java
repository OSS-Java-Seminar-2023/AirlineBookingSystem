package hr.OSSAirline.models;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity(name = "flight")
public class Flight {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name = "from_id")
    private Airport from;
    @ManyToOne
    @JoinColumn(name = "to_id")
    private Airport to;
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
    @Column
    private java.sql.Date date;
    @Column
    private java.sql.Time time;
    @Column
    private String gate;
    @Column
    private java.sql.Time duration;
}

