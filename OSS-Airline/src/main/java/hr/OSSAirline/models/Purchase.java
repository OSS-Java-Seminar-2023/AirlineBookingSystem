package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;


import java.util.Date;

@Setter
@Getter
@Entity
public class purchase {
    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private hr.OSSAirline.models.user user;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private hr.OSSAirline.models.ticket ticket;
    @Column
    private String paymentInfo;
    @Column
    private Date paymentDate;

}
