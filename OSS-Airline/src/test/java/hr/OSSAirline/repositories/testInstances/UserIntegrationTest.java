package hr.OSSAirline.repositories.testInstances;

import hr.OSSAirline.models.User;
import hr.OSSAirline.repositories.RepositoryTestBase;
import hr.OSSAirline.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

@Testcontainers
@SpringBootTest
public class UserIntegrationTest  extends RepositoryTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    void userSaveGetByUsernameTest() {
        User testUser = new User();
        testUser.setUsername("Mauro");
        testUser.setPassword("ante123");
        testUser.setEmail("ante@oss.hr");


        userRepository.save(testUser);

        Optional<User> out = userRepository.findUserByUsername(testUser.getUsername());
        Assertions.assertEquals(out.get().getUsername(), testUser.getUsername());
    }

    @Test
    void userSaveGetByUsernameTest2() {
        User testUser = new User();
        testUser.setUsername("Mate");
        testUser.setPassword("mate123");
        testUser.setEmail("mate@oss.hr");


        userRepository.save(testUser);

        Optional<User> out = userRepository.findUserByUsername(testUser.getUsername());
        Assertions.assertEquals(out.get().getUsername(), testUser.getUsername());
    }

    @Test
    void userSaveGetByUsernameTest3() {
        User testUser = new User();
        testUser.setUsername("Jure");
        testUser.setPassword("jure123");
        testUser.setEmail("jure@oss.hr");


        userRepository.save(testUser);

        Optional<User> out = userRepository.findUserByUsername(testUser.getUsername());
        Assertions.assertEquals(out.get().getUsername(), testUser.getUsername());
    }

    @Test
    void userSaveGetByUsernameTest4() {
        User testUser = new User();
        testUser.setUsername("Filip");
        testUser.setPassword("filip123");
        testUser.setEmail("filip@oss.hr");


        userRepository.save(testUser);

        Optional<User> out = userRepository.findUserByUsername(testUser.getUsername());
        Assertions.assertEquals(out.get().getUsername(), testUser.getUsername());
    }

    @Test
    void userSaveGetByUsernameTest5() {
        User testUser = new User();
        testUser.setUsername("Roko");
        testUser.setPassword("roko123");
        testUser.setEmail("roko@oss.hr");


        userRepository.save(testUser);

        Optional<User> out = userRepository.findUserByUsername(testUser.getUsername());
        Assertions.assertEquals(out.get().getUsername(), testUser.getUsername());
    }

    @Test
    void userSaveGetNullByUsernameTest() {
        User testUser = new User();
        testUser.setUsername("Ante");
        testUser.setPassword("ante123");
        testUser.setEmail("ante@oss.hr");

        var wrong_username = "wrong";

        userRepository.save(testUser);

        var out = userRepository.findUserByUsername(wrong_username);
        Assertions.assertTrue(out.isEmpty());
    }

    @Test
    void userSaveGetNullByUsernameTest2() {
        User testUser = new User();
        testUser.setUsername("Ante");
        testUser.setPassword("ante123");
        testUser.setEmail("ante@oss.hr");

        var wrong_username = "antee";

        userRepository.save(testUser);

        var out = userRepository.findUserByUsername(wrong_username);
        Assertions.assertTrue(out.isEmpty());
    }

    @Test
    void userSaveGetNullByUsernameTest3() {
        User testUser = new User();
        testUser.setUsername("Ante");
        testUser.setPassword("ante123");
        testUser.setEmail("ante@oss.hr");

        var wrong_username = "a";

        userRepository.save(testUser);

        var out = userRepository.findUserByUsername(wrong_username);
        Assertions.assertTrue(out.isEmpty());
    }

    @Test
    void userSaveGetNullByUsernameTest4() {
        User testUser = new User();
        testUser.setUsername("Ante");
        testUser.setPassword("ante123");
        testUser.setEmail("ante@oss.hr");

        var wrong_username = "ant";

        userRepository.save(testUser);

        var out = userRepository.findUserByUsername(wrong_username);
        Assertions.assertTrue(out.isEmpty());
    }

    @Test
    void userSaveGetNullByUsernameTest5() {
        User testUser = new User();
        testUser.setUsername("Ante");
        testUser.setPassword("ante123");
        testUser.setEmail("ante@oss.hr");

        var wrong_username = "anteante";

        userRepository.save(testUser);

        var out = userRepository.findUserByUsername(wrong_username);
        Assertions.assertTrue(out.isEmpty());
    }
}