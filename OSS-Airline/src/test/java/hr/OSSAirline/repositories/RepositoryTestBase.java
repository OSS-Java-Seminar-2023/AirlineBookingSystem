package hr.OSSAirline.repositories;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest

public class RepositoryTestBase {
    static MySQLContainer<?> mySql = new MySQLContainer<>(
            "mysql:8.0"
    );

    @BeforeAll
     static void beforeAll() {
        mySql.start();
    }

    @AfterAll
    static void afterAll() {
        mySql.stop();
    }
}
