package hr.OSSAirline.repositories;

import hr.OSSAirline.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User, String> {
=======
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query
    User getUserByUsername(String username);
>>>>>>> eb38abf (tests)
}
