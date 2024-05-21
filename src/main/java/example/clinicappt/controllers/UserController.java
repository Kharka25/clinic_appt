package example.clinicappt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import example.clinicappt.models.User;
import example.clinicappt.services.UserService;

@RestController // informs Spring that class is a RestController Component that can handle HTTP requests
@RequestMapping("/user") // indicates address requests must contain to access this controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/auth/signup")
    private ResponseEntity<Void> signUp(@RequestBody User newUserRequest, UriComponentsBuilder ucb) {
        User newUser = new User(newUserRequest.getFirstName(), newUserRequest.getLastName(), newUserRequest.getAge(), newUserRequest.getEmail());
        User savedUser = userService.createUser(newUser);
        URI locationOfNewUser = ucb.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUserProfile(@PathVariable Integer id) {
        User user = userService.getProfile(id);
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }


}
