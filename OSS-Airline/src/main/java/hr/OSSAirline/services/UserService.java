package hr.OSSAirline.services;

import hr.OSSAirline.dto.UserDto;
import hr.OSSAirline.exceptions.PasswordException;
import hr.OSSAirline.mappers.UserMapper;
import hr.OSSAirline.repositories.ReservationRepository;
import hr.OSSAirline.repositories.UserRepository;
import hr.OSSAirline.utils.MailConstants;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final ReservationRepository reservationRepository;

    public void registerUser(UserDto user) throws MessagingException {
        if(usernameTaken(user.getUsername())){
            throw new RuntimeException("Username taken!");
        }
        if(emailTaken(user.getEmail())){
            throw new RuntimeException("Email is already in use!");
        }
        encodeAndSetPassword(user);

        emailService.sendWelcomeEmail(user.getEmail());
        user.setIsAdmin("false");
        userRepository.save(userMapper.toEntity(user));
    }

    private static void encodeAndSetPassword(UserDto user) {
        var passwordEncoder = new BCryptPasswordEncoder();
        var hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
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
        var users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getAllUsersNotAdmins() {
        var users = userRepository.findByIsAdmin("false");
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getAllAdmins() {
        var users = userRepository.findByIsAdmin("true");
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserByUsername(String username){
        return userMapper.toDto(userRepository.getUserByUsername(username));
    }

    public UserDto getUserById(String id){
        return userMapper.toDto(userRepository.getReferenceById(id));
    }

    public void changePassword(String username, String newPassword, String oldPassword, String passConfirm) throws PasswordException {
        if(!newPassword.equals(passConfirm)){
            throw new PasswordException("New passwords not matching!");
        }
        var passwordEncoder = new BCryptPasswordEncoder();
        var user = userRepository.getUserByUsername(username);
        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new PasswordException("Current password not matching!");
        }
        var hashedPassword = passwordEncoder.encode(newPassword);
        userRepository.changePassword(username, hashedPassword);
    }

    public void deleteUser(String id, UserDto loggedInUser){
        if(id.equals(loggedInUser.getId())){
            throw new RuntimeException("You can not delete your self.");
        }
        if(userRepository.existsById(id) && !reservationRepository.existsByUser_Id(id)){
            userRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Can not delete user, because user has reserved tickets.");
        }
    }

    public void updateUserToAdmin(String id, UserDto updatesUser){
        if(userRepository.existsById(id) && !reservationRepository.existsByUser_Id(id)){
            if (getAllAdmins().size() == 1 &&
                    getAllAdmins().stream().anyMatch(admin -> admin.getId().equals(updatesUser.getId()))) {
                throw new RuntimeException("Can not update admin, because there has to be one admin.");
            }
            var user = userRepository.findById(id).get();
            updatesUser.setPassword(user.getPassword());
            userRepository.save(userMapper.toEntity(updatesUser));
        }
        else{
            throw new RuntimeException("Can not update user, because user has reserved tickets.");
        }
    }
}