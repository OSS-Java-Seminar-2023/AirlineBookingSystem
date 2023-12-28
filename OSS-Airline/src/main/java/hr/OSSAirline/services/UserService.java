package hr.OSSAirline.services;

import hr.OSSAirline.dto.UserDto;
import hr.OSSAirline.mappers.UserMapper;
import hr.OSSAirline.models.User;
import hr.OSSAirline.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void registerUser(UserDto user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(UserMapper.INSTANCE.toEntity(user));
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

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}