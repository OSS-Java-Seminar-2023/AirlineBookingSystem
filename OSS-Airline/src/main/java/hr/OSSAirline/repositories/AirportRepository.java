package hr.OSSAirline.repositories;

import hr.OSSAirline.models.airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<airplane, String> {
}
