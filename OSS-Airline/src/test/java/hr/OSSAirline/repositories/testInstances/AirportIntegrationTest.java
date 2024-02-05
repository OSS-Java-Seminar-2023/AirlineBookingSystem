package hr.OSSAirline.repositories.testInstances;

import hr.OSSAirline.models.Airport;
import hr.OSSAirline.repositories.AirportRepository;
import hr.OSSAirline.repositories.RepositoryTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public class AirportIntegrationTest extends RepositoryTestBase {
    @Autowired
    AirportRepository airportRepository;

    @Test
    public void airportSaveAndGetByName(){
        var testAirport = new Airport();
        testAirport.setIATA("SPU");
        testAirport.setName("Split");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByName(testAirport.getName());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByName2(){
        var testAirport = new Airport();
        testAirport.setIATA("ZRN");
        testAirport.setName("Zrnovnica");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByName(testAirport.getName());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByName3(){
        var testAirport = new Airport();
        testAirport.setIATA("MAK");
        testAirport.setName("Makarska");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByName(testAirport.getName());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByName4(){
        var testAirport = new Airport();
        testAirport.setIATA("KAS");
        testAirport.setName("Kastela");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByName(testAirport.getName());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByName5(){
        var testAirport = new Airport();
        testAirport.setIATA("AAA");
        testAirport.setName("Karepovac");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByName(testAirport.getName());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByIATA(){
        var testAirport = new Airport();
        testAirport.setIATA("AAA");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByIATA(testAirport.getIATA());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByIATA2(){
        var testAirport = new Airport();
        testAirport.setIATA("FRT");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByIATA(testAirport.getIATA());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByIATA3(){
        var testAirport = new Airport();
        testAirport.setIATA("ZUT");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByIATA(testAirport.getIATA());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByIATA4(){
        var testAirport = new Airport();
        testAirport.setIATA("RTZ");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByIATA(testAirport.getIATA());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetByIATA5(){
        var testAirport = new Airport();
        testAirport.setIATA("MTZ");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var airport = airportRepository.getByIATA(testAirport.getIATA());
        Assertions.assertEquals(airport.getName(), testAirport.getName());
    }

    @Test
    public void airportSaveAndGetNullByName(){
        var testAirport = new Airport();
        testAirport.setIATA("AAA");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var wrong = "null";

        var airport = airportRepository.getByName(wrong);
        Assertions.assertNull(airport);
    }

    @Test
    public void airportSaveAndGetNullByName2(){
        var testAirport = new Airport();
        testAirport.setIATA("AAA");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var wrong = "srinjin";

        var airport = airportRepository.getByName(wrong);
        Assertions.assertNull(airport);
    }

    @Test
    public void airportSaveAndGetNullByName3(){
        var testAirport = new Airport();
        testAirport.setIATA("AAA");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var wrong = "sri";

        var airport = airportRepository.getByName(wrong);
        Assertions.assertNull(airport);
    }

    @Test
    public void airportSaveAndGetNullByName4(){
        var testAirport = new Airport();
        testAirport.setIATA("AAA");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var wrong = "AAA";

        var airport = airportRepository.getByName(wrong);
        Assertions.assertNull(airport);
    }

    @Test
    public void airportSaveAndGetNullByName5(){
        var testAirport = new Airport();
        testAirport.setIATA("AAA");
        testAirport.setName("Srinjine");
        airportRepository.save(testAirport);

        var wrong = "srinjineee";

        var airport = airportRepository.getByName(wrong);
        Assertions.assertNull(airport);
    }
}
