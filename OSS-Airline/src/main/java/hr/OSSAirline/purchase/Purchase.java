package hr.OSSAirline.purchase;

import hr.OSSAirline.ticket.Ticket;
import hr.OSSAirline.user.User;
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
    @Column
    private String id = UUID.randomUUID().toString();
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
