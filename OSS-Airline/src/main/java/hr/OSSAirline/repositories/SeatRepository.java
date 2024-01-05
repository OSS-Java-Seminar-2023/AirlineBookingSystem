package hr.OSSAirline.repositories;

import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFlightId(String flight_id);
    List<Seat> findByIdIn(List<String> seat_id);
    Optional<Seat> findById(String id);
}
