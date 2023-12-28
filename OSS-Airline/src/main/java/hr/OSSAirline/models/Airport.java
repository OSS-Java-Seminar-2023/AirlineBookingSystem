package hr.OSSAirline.models;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity(name = "airport")
public class Airport {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String name;
    @Column
    private String IATA;

}

