package hr.OSSAirline.services;

import hr.OSSAirline.models.User;
import hr.OSSAirline.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public void registerUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);
    }

    public boolean usernameTaken(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.isPresent();
    }

    public boolean emailTaken(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent();
    }

    public boolean authenticate(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent()) {
            var user = userOptional.get();

            if(passwordEncoder.matches(password, user.getPassword())){
                return true;
            }
//            throw new RuntimeException("Wrong password!");
        }
//        throw new RuntimeException("Wrong username!");
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();

//    public void saveUser(user user){
//        userRepository.save(user);
//    }
//
//    public user getUserByUsername(String username){
//        return userRepository.getUserByUsername(username);
//    }
    }
}