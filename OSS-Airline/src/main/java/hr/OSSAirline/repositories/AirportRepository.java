package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airplane, UUID> {
}
