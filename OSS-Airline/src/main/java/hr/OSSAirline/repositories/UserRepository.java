package hr.OSSAirline.repositories;

import hr.OSSAirline.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, String> {
    Optional<user> findByEmailAndPassword(String email, String password);
    Optional<user> findByEmail(String email);

}
