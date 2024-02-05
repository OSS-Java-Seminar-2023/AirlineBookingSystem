package hr.OSSAirline.repositories.testInstances;

import hr.OSSAirline.models.Airplane;
import hr.OSSAirline.repositories.AirplaneRepository;
import hr.OSSAirline.repositories.RepositoryTestBase;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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
        Assertions.assertEquals(out.getRegistration(), testAirplane.getRegistration(), "Test Passed");
    }

    @Test
    public void airplaneSaveAndGetByRegistration2(){
        var testAirplane = new Airplane();
        testAirplane.setRegistration("12344325");
        testAirplane.setModel("B2732");
        testAirplane.setBusinessSeats(1);
        testAirplane.setFirstSeats(1);
        testAirplane.setEconomySeats(1);
        airplaneRepository.save(testAirplane);

        var out = airplaneRepository.getAirplaneByRegistration(testAirplane.getRegistration());
        Assertions.assertEquals(out.getRegistration(), testAirplane.getRegistration(), "Test Passed");
    }

    @Test
    public void airplaneSaveAndGetByRegistration3(){
        var testAirplane = new Airplane();
        testAirplane.setRegistration("4325");
        testAirplane.setModel("B");
        testAirplane.setBusinessSeats(1);
        testAirplane.setFirstSeats(1);
        testAirplane.setEconomySeats(1);
        airplaneRepository.save(testAirplane);

        var out = airplaneRepository.getAirplaneByRegistration(testAirplane.getRegistration());
        Assertions.assertEquals(out.getRegistration(), testAirplane.getRegistration(), "Test Passed");
    }

    @Test
    public void airplaneSaveAndGetNullByRegistration(){
        var testAirplane = new Airplane();
        testAirplane.setRegistration("12878345");
        testAirplane.setModel("B272");
        testAirplane.setBusinessSeats(30);
        testAirplane.setFirstSeats(20);
        testAirplane.setEconomySeats(40);
        airplaneRepository.save(testAirplane);

        var wrong_reg = "wrong";

        var out = airplaneRepository.getAirplaneByRegistration(wrong_reg);
        Assertions.assertNull(out, "Test Passed");
    }

    @Test
    public void airplaneSaveAndGetNullByRegistration2(){
        var testAirplane = new Airplane();
        testAirplane.setRegistration("12323445");
        testAirplane.setModel("B272");
        testAirplane.setBusinessSeats(30);
        testAirplane.setFirstSeats(20);
        testAirplane.setEconomySeats(40);
        airplaneRepository.save(testAirplane);

        var wrong_reg = "wrong";

        var out = airplaneRepository.getAirplaneByRegistration(wrong_reg);
        Assertions.assertNull(out, "Test Passed");
    }

    @Test
    public void airplaneSaveAndGetNullByRegistration3(){
        var testAirplane = new Airplane();
        testAirplane.setRegistration("123565645");
        testAirplane.setModel("B272");
        testAirplane.setBusinessSeats(30);
        testAirplane.setFirstSeats(20);
        testAirplane.setEconomySeats(40);
        airplaneRepository.save(testAirplane);

        var wrong_reg = "wrong";

        var out = airplaneRepository.getAirplaneByRegistration(wrong_reg);
        Assertions.assertNull(out, "Test Passed");
    }

    @Test
    public void airplaneSaveAndGetNullByRegistration4(){
        var testAirplane = new Airplane();
        testAirplane.setRegistration("12767686345");
        testAirplane.setModel("B272");
        testAirplane.setBusinessSeats(30);
        testAirplane.setFirstSeats(20);
        testAirplane.setEconomySeats(40);
        airplaneRepository.save(testAirplane);

        var wrong_reg = "wrong";

        var out = airplaneRepository.getAirplaneByRegistration(wrong_reg);
        Assertions.assertNull(out, "Test Passed");
    }
}
