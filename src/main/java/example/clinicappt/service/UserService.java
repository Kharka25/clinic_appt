package example.clinicappt.service;

import example.clinicappt.UserServiceInterface;
import example.clinicappt.models.User;

public class UserService implements UserServiceInterface {
    @Override
    public User getUserProfile() {
        return new User();
    }
}
