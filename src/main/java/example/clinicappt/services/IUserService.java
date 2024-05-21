package example.clinicappt.services;

import example.clinicappt.models.User;

public interface IUserService {
    User getProfile(Integer userId);

    User createUser(User newUser);
}
