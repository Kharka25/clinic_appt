package example.clinicappt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.clinicappt.models.User;
import example.clinicappt.service.UserService;

@RestController // informs Spring that class is a RestController Component that can handle HTTP requests
@RequestMapping("/user") // indicates address requests must contain to access this controller
public class UserController {
    UserService userService;
    @GetMapping("/auth/get-profile/{id}")
    private ResponseEntity<User> getProfile() {
        User user = new User();
        return ResponseEntity.ok(user);
    }
}
