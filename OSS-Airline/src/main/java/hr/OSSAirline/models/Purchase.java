package hr.OSSAirline.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @Column
    private String paymentInfo;
    @Column
    private Date paymentDate;

}
