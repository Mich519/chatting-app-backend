package com.example.demo.configuration;

import com.example.demo.channel.model.Channel;
import com.example.demo.channel.service.ChannelService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import com.example.demo.user.utils.UserRole;
import com.example.demo.userchannel.model.UserChannel;
import com.example.demo.userchannel.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {

    private final UserService userService;
    private final ChannelService channelService;
    private final UserChannelService userChannelService;

    @Autowired
    public DataInitializer(UserService userService, ChannelService channelService, UserChannelService userChannelService) {
        this.userService = userService;
        this.channelService = channelService;
        this.userChannelService = userChannelService;
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
                .build();

        User user2 = User.builder()
                .username("adamix123")
                .password("adam")
                .role(UserRole.USER)
                .build();

        User user3 = User.builder()
                .username("pro_player_-_-")
                .password("pro_player_-_-")
                .role(UserRole.USER)
                .build();

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);


        UserChannel uc1 = UserChannel.builder()
                .user(user1)
                .channel(channel1)
                .build();

        userChannelService.addUserChannel(uc1);
        channel1.setUserChannels(List.of(uc1));
        user1.setUserChannels(List.of(uc1));


    }
}
