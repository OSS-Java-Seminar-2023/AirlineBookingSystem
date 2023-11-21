package hr.OSSAirline.repositories;

import hr.OSSAirline.models.passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<passenger, String> {
}
