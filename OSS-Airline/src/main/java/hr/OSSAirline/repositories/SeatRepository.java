package hr.OSSAirline.repositories;

import hr.OSSAirline.models.seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<seat, String> {
}
