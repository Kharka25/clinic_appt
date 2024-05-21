package example.clinicappt.repositories;

import org.springframework.data.repository.CrudRepository;

import example.clinicappt.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
