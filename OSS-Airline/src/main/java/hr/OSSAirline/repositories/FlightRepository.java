package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
