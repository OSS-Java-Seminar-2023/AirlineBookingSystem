package hr.OSSAirline.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
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
