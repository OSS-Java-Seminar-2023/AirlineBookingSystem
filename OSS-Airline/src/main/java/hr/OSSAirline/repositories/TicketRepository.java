package hr.OSSAirline.repositories;

import hr.OSSAirline.models.ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<ticket, String> {
}
