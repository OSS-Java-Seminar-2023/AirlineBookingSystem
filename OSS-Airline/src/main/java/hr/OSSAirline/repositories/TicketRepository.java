package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Reservation;
import hr.OSSAirline.models.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findByFlightId(String id);
    @Transactional
    @Modifying
    @Query("UPDATE ticket t SET t.reservation = :reservation WHERE t.id = :ticketId")
    void updateReservationField(String ticketId, Reservation reservation);

    boolean existsTicketsByFlight_Id(String flight_id);
}
