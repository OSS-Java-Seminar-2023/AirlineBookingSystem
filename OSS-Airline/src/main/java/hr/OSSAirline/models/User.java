package hr.OSSAirline.models;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "user", schema = "Airline")
public class User {
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column(unique = true)
    private String email;
}
