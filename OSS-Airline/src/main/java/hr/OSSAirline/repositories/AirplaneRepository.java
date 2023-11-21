package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, String> {

    public Airplane getAirplaneByRegistration(String registration);
}
