package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "user", schema = "Airline")
public class User {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
}
