package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
}
