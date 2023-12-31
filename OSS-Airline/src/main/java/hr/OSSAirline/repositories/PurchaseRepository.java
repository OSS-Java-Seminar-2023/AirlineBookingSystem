package hr.OSSAirline.repositories;

import hr.OSSAirline.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {
}
