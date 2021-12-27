package com.example.demo.userchannel.controller;

import com.example.demo.channel.model.Channel;
import com.example.demo.channel.service.ChannelService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import com.example.demo.userchannel.dto.UserChannelDto;
import com.example.demo.userchannel.model.UserChannel;
import com.example.demo.userchannel.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userchannel")
public class UserChannelController {

    private final UserChannelService userChannelService;
    private final UserService userService;
    private final ChannelService channelService;

    @Autowired
    public UserChannelController(UserChannelService userChannelService,
                                 UserService userService,
                                 ChannelService channelService) {
        this.userChannelService = userChannelService;
        this.userService = userService;
        this.channelService = channelService;
    }

    @GetMapping
    private ResponseEntity<List<UserChannel>> getAllUserChannel() {
        return ResponseEntity.ok(userChannelService.findAllUserChannel());
    }

    @PostMapping
    public ResponseEntity<UserChannel> addUserChannel(@RequestBody UserChannelDto userChannelDto) {

        Optional<Channel> channel = channelService.findChannel(userChannelDto.getChannelId());
        Optional<User> user = userService.findUser(userChannelDto.getUserId());

        if(user.isPresent() && channel.isPresent() && userChannelService.findUserChannelByUserAndChannel(user.get(), channel.get()).isEmpty()) {
                UserChannel newUserChannel = userChannelDto.toEntity(user.get(), channel.get());
                userChannelService.addUserChannel(newUserChannel);
                user.get().getUserChannels().add(newUserChannel);
                channel.get().getUserChannels().add(newUserChannel);
        }
        return ResponseEntity.created(URI.create("/api/userchannel")).build();
    }
}
