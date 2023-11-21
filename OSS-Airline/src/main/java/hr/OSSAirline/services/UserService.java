package hr.OSSAirline.services;

import hr.OSSAirline.models.user;
import hr.OSSAirline.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;


    public void registerUser(user user) {
        userRepository.save(user);
    }

    public boolean authenticate(String email, String password) {
        Optional<user> userOptional = userRepository.findByEmailAndPassword(email, password);
        return userOptional.isPresent();
    }

    public List<user> getAllUsers() {
        return userRepository.findAll();
    }
}
