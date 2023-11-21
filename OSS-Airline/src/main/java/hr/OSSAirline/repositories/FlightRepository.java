package hr.OSSAirline.repositories;

import hr.OSSAirline.models.flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<flight, String> {
}
