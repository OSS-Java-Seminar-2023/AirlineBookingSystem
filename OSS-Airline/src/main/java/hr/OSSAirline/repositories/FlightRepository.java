package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Airport;
import hr.OSSAirline.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    public List<Flight> searchFlightsByFromAndToAndDate(Airport from, Airport to, Date date);
}
