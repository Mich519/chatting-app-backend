package com.example.demo.user.controller;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import com.example.demo.userchannel.model.UserChannel;
import com.example.demo.userchannel.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final UserChannelService userChannelService;

    @Autowired
    public UserController(UserService userService, UserChannelService userChannelService) {
        this.userService = userService;
        this.userChannelService = userChannelService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> findUser(@PathVariable int userId) {
        Optional<User> user = userService.findUser(userId);
        return user
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param channelId id of the channel
     * @return list of all users that belong to a channel
     */
    @GetMapping("/channels/{channelId}/users")
    public ResponseEntity<List<User>> findAllUsersInChannel(@PathVariable int channelId) {
        List<UserChannel> userChannels = userChannelService.findAllUserChannelByChannelId(channelId);
        List<User> allUsersInChannel = userChannels.stream().map(UserChannel::getUser).toList();
        return ResponseEntity.ok(allUsersInChannel);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        userService.addUser(newUser);
        return ResponseEntity.created(URI.create("/api/users")).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        Optional<User> userToDelete = userService.findUser(id);
        userToDelete.ifPresent(userService::deleteUser);
        return ResponseEntity.ok().build();
    }
}
