package example.clinicappt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import example.clinicappt.models.User;
import example.clinicappt.repositories.UserRepository;

@Component
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getProfile(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()) {
            return null;
        }

        return optionalUser.get();
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }
}
