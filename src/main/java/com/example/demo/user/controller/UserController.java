package com.example.demo.user.controller;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUser(@PathVariable Integer id) {
        Optional<User> user = userService.findUser(id);
        return user
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        userService.addUser(newUser);
        return ResponseEntity.created(URI.create("/api/users")).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        Optional<User> userToDelete = userService.findUser(id);
        userToDelete.ifPresent(userService::deleteUser);
        return ResponseEntity.ok().build();
    }
}
