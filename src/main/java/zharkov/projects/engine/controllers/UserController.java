package zharkov.projects.engine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zharkov.projects.engine.services.UserService;
import zharkov.projects.model.entities.UserEntity;
import zharkov.projects.model.frontend.AuthenticationContainer;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final UserService service = new UserService();

    @GetMapping("/user/auth")
    public ResponseEntity<UserEntity> getAllPublications(@RequestHeader("Login") String login,
                                                         @RequestHeader("Password") String password) {
        UserEntity user = service.getUserByCredentials(new AuthenticationContainer(login, password));
        if (user == null) {
            return new ResponseEntity<>((UserEntity) null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        service.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //@PutMapping("/user")

}
