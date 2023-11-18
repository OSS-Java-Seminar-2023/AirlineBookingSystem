package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}