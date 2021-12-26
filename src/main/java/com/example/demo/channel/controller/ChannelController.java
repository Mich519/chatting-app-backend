package com.example.demo.channel.controller;

import com.example.demo.channel.model.Channel;
import com.example.demo.channel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/channels")
public class ChannelController {

    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public ResponseEntity<List<Channel>> findAllChannels() {
        return ResponseEntity.ok(channelService.findAllChannels());
    }

    @GetMapping("{id}")
    public ResponseEntity<Channel> findChannel(@PathVariable int id) {
        Optional<Channel> channel = channelService.findChannel(id);
        return channel
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Channel> addChannel(@RequestBody Channel newChannel) {
        channelService.addChannel(newChannel);
        return ResponseEntity.created(URI.create("/api/channels")).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Channel> deleteChannel(@PathVariable int id) {
        Optional<Channel> channelToDelete = channelService.findChannel(id);
        channelToDelete.ifPresent(channelService::deleteChannel);
        return ResponseEntity.ok().build();
    }
}
