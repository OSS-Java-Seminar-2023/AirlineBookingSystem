package hr.OSSAirline.services;

import hr.OSSAirline.dto.UserDto;
import hr.OSSAirline.mappers.UserMapper;
import hr.OSSAirline.models.User;
import hr.OSSAirline.repositories.UserRepository;
import hr.OSSAirline.utils.MailConstants;
import jakarta.mail.MessagingException;
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

    private final EmailService emailService;

    public void registerUser(UserDto user) throws MessagingException {
        if(usernameTaken(user.getUsername())){
            throw new RuntimeException("Username taken!");
        }
        if(emailTaken(user.getEmail())){
            throw new RuntimeException("Email is already in use!");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        emailService.sendEmail(user.getEmail(), MailConstants.MAIL_SUBJECT, MailConstants.MAIL_BODY);

        userRepository.save(UserMapper.INSTANCE.toEntity(user));
    }

    public boolean usernameTaken(String username){
        var userOptional = userRepository.findByUsername(username);
        return userOptional.isPresent();
    }

    public boolean emailTaken(String email){
        var userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent();
    }

    public void authenticate(String username, String password) {
        if(!usernameTaken(username)){
            throw new RuntimeException("Wrong username or password!");
        }
        var passwordEncoder = new BCryptPasswordEncoder();

        var userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent()) {
            var user = userOptional.get();

            if(!passwordEncoder.matches(password, user.getPassword())){
                throw new RuntimeException("Wrong username or password!");
            }
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}