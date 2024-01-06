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
    @Query("SELECT f FROM flight f " +
            "WHERE f.from = :from " +
            "AND f.to = :to " +
            "AND f.date = :date " +
            "AND (f.time > CURRENT_TIME() OR f.date > CURRENT_DATE())")
    List<Flight> searchFlightsByFromAndToAndDate(@Param("from") Airport from, @Param("to") Airport to, @Param("date") Date date);

    @Query("SELECT f FROM flight f " +
            "WHERE f.from = :from " +
            "AND f.to = :to " +
            "AND f.date = (SELECT MAX(ff.date) FROM flight ff " +
            "              WHERE ff.from = :from " +
            "              AND ff.to = :to " +
            "              AND ff.date < :givenDate " +
            "              AND ff.date > CURRENT_DATE())")
    List<Flight> findAllFlightsForFirstDateBeforeGivenDate(@Param("from") Airport from, @Param("to") Airport to, @Param("givenDate") Date givenDate);



    @Query("SELECT f FROM flight f " +
            "WHERE f.from = :from " +
            "AND f.to = :to " +
            "AND f.date = (SELECT MIN(ff.date) FROM flight ff " +
            "              WHERE ff.from = :from " +
            "              AND ff.to = :to " +
            "              AND ff.date > :givenDate)")
    List<Flight> findAllFlightsForFirstDateAfterGivenDate(@Param("from") Airport from, @Param("to") Airport to, @Param("givenDate") Date givenDate);

}
