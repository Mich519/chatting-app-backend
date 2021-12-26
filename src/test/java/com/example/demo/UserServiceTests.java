package com.example.demo;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import com.example.demo.user.utils.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void addUserToRepo() {
        User testUser = User.builder()
                .username("Username1")
                .password("Password")
                .role(UserRole.USER)
                .build();

        User userInDb = userService.addUser(testUser);
        Assertions.assertEquals(testUser, userInDb);
    }
}
