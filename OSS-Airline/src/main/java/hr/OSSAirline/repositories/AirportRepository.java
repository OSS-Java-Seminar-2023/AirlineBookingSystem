package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Airport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    public Optional<Airport> findByName(String name);

    public Boolean existsByIATA(String IATA);
}
