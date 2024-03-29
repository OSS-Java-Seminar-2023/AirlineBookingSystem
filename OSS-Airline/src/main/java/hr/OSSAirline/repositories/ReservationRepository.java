package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Reservation;
import hr.OSSAirline.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findReservationsByUser(User user);

    Reservation findReservationsById(String id);

    Boolean existsByUser_Id(String id);
}
