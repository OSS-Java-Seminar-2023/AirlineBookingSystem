package hr.OSSAirline.services;

import hr.OSSAirline.models.User;
import hr.OSSAirline.models.User;
import hr.OSSAirline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;

    @Autowired
    private UserRepository userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }

    public User getUserByUsername(String username){
        return userRepo.getUserByUsername(username);
    }

    public void registerUser(User user){
        userRepository.save(user);
    }
}
