package com.example.demo.configuration;

import com.example.demo.channel.model.Channel;
import com.example.demo.channel.service.ChannelService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import com.example.demo.user.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {

    private final UserService userService;
    private final ChannelService channelService;

    @Autowired
    public DataInitializer(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @PostConstruct
    public void init() {
        Channel channel1 = Channel.builder()
                .name("MyChannel")
                .build();

        channelService.addChannel(channel1);

        User user1 = User.builder()
                .username("1Michał")
                .password("admin")
                .role(UserRole.ADMIN)
                .channels(List.of(channel1))
                .build();

        User user2 = User.builder()
                .username("adamix123")
                .password("adam")
                .role(UserRole.USER)
                .channels(List.of(channel1))
                .build();

        User user3 = User.builder()
                .username("pro_player_-_-")
                .password("pro_player_-_-")
                .role(UserRole.USER)
                .channels(List.of(channel1))
                .build();

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        channel1.setUsers(List.of(user1, user2, user3));
    }
}
