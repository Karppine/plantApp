package com.example.plant_watering_monitor.user;

import com.example.plant_watering_monitor.user.model.UpdateUserCommand;
import com.example.plant_watering_monitor.user.model.User;
import com.example.plant_watering_monitor.user.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 No UserDTO made at the moment, maybe add later.
 */

@RestController
public class UserController {

    private final CreateUserService createUserService;

    private final GetUsersService getUsersService;

    private final GetUserService getUserService;

    private final UpdateUserService updateUserService;

    private final DeleteUserService deleteUserService;

    public UserController(CreateUserService createUserService,
                          GetUsersService getUsersService,
                          GetUserService getUserService,
                          UpdateUserService updateUserService,
                          DeleteUserService deleteUserService) {
        this.createUserService = createUserService;
        this.getUsersService = getUsersService;
        this.getUserService = getUserService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return createUserService.execute(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {return getUsersService.execute(null);}

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {return getUserService.execute(id);}

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return updateUserService.execute(new UpdateUserCommand(id, user));
    }

     @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {return deleteUserService.execute(id);}


}
