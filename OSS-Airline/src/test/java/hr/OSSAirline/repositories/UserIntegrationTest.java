package hr.OSSAirline.repositories;

import hr.OSSAirline.models.User;
import hr.OSSAirline.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

@Testcontainers
@SpringBootTest
public class UserIntegrationTest  extends RepositoryTestBase{

    @Autowired
    private UserService userService;

    @Test
    void userDataTest() {
        User testUser = new User();
        testUser.setUsername("Ante");
        testUser.setPassword("ante123");
        testUser.setEmail("ante@oss.hr");
        userService.saveUser(testUser);
        User getUser = userService.getUserByUsername(testUser.getUsername());

        Assertions.assertEquals(getUser, testUser);
    }
}