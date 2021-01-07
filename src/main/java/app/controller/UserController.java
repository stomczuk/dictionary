package app.controller;

import app.entity.User;
import app.exception.domain.ExceptionHandling;
import app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionHandling {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String showSomething() {
        return "application works";
    }

    @PostMapping("sign-up")
    public ResponseEntity sighUp(@RequestBody User user) {
        if (user != null) {
            try {
                userService.save(user);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
