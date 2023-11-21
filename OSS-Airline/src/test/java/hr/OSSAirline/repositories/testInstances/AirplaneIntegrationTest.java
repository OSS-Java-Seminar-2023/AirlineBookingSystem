package hr.OSSAirline.repositories.testInstances;

import hr.OSSAirline.models.Airplane;
import hr.OSSAirline.repositories.AirplaneRepository;
import hr.OSSAirline.repositories.RepositoryTestBase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public class AirplaneIntegrationTest extends RepositoryTestBase {

    @Autowired
    AirplaneRepository airplaneRepository;

    @Test
    public void airplaneSaveAndGetByRegistration(){
        var testAirplane = new Airplane();
        testAirplane.setRegistration("12345");
        testAirplane.setModel("B272");
        testAirplane.setBusinessSeats(30);
        testAirplane.setFirstSeats(20);
        testAirplane.setEconomySeats(40);
        airplaneRepository.save(testAirplane);

        var out = airplaneRepository.getAirplaneByRegistration(testAirplane.getRegistration());
        Assert.assertEquals("Test Passed", out.getRegistration(), testAirplane.getRegistration());
    }
}
