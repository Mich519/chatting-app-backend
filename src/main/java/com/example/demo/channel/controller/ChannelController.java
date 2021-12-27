package com.example.demo.channel.controller;

import com.example.demo.channel.model.Channel;
import com.example.demo.channel.service.ChannelService;
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
public class ChannelController {

    private final ChannelService channelService;

    private final UserChannelService userChannelService;

    @Autowired
    public ChannelController(ChannelService channelService, UserChannelService userChannelService) {
        this.channelService = channelService;
        this.userChannelService = userChannelService;
    }

    @GetMapping("/channels")
    public ResponseEntity<List<Channel>> findAllChannels() {
        return ResponseEntity.ok(channelService.findAllChannels());
    }

    @GetMapping("/channels/{channelId}")
    public ResponseEntity<Channel> findChannel(@PathVariable int channelId) {
        Optional<Channel> channel = channelService.findChannel(channelId);
        return channel
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param userId id of the user
     * @return list of channels the user belongs to
     */
    @GetMapping("/users/{userId}/channels")
    public ResponseEntity<List<Channel>> findAllChannelsJoinedByUser(@PathVariable int userId) {
        List<UserChannel> userChannels = userChannelService.findAllUserChannelByUserId(userId);
        List<Channel> channelsJoinedByUser = userChannels.stream().map(UserChannel::getChannel).toList();
        return ResponseEntity.ok(channelsJoinedByUser);
    }

    @PostMapping("/channels")
    public ResponseEntity<Channel> addChannel(@RequestBody Channel newChannel) {
        channelService.addChannel(newChannel);
        return ResponseEntity.created(URI.create("/api/channels")).build();
    }

    @DeleteMapping("/channels/{channelId}")
    public ResponseEntity<Channel> deleteChannel(@PathVariable int channelId) {
        Optional<Channel> channelToDelete = channelService.findChannel(channelId);
        channelToDelete.ifPresent(channelService::deleteChannel);
        return ResponseEntity.ok().build();
    }
}
