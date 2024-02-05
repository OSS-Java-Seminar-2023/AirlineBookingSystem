package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    Optional<Airport> findByName(String name);

    Airport getByName(String name);
    Airport getByIATA(String iata);

    Boolean existsByIATA(String IATA);
}
