package zharkov.projects.engine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zharkov.projects.engine.services.UserService;
import zharkov.projects.model.entities.UserEntity;
import zharkov.projects.model.frontend.AuthenticationContainer;
import zharkov.projects.model.frontend.User;
import zharkov.projects.model.frontend.UserSensitive;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final UserService service = new UserService();

    @GetMapping("/user/auth")
    public ResponseEntity<UserSensitive> getUserByCredentials(@RequestHeader("Login") String login,
                                                              @RequestHeader("Password") String password) {
        UserSensitive user = service.getUserByCredentials(new AuthenticationContainer(login, password));
        if (user == null) {
            return new ResponseEntity<>((UserSensitive) null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = service.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>((User) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserSensitive> createUser(@RequestBody UserEntity entity) {
        UserSensitive user = service.createUser(entity);
        if (user == null) {
            return new ResponseEntity<>((UserSensitive) null, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //@PutMapping("/user")

}
