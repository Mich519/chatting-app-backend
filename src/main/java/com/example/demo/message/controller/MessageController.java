package com.example.demo.message.controller;

import com.example.demo.message.dto.MessageDto;
import com.example.demo.message.model.Message;
import com.example.demo.message.service.MessageService;
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
public class MessageController {

    private final MessageService messageService;

    private final UserChannelService userChannelService;

    @Autowired
    public MessageController(MessageService messageService, UserChannelService userChannelService) {
        this.messageService = messageService;
        this.userChannelService = userChannelService;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageInChannel(@PathVariable long messageId) {
        Optional<Message> message = messageService.findMessageById(messageId);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/channels/{channelId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesInChannel(@PathVariable int channelId) {
        List<UserChannel> userChannels = userChannelService.findAllUserChannelByChannelId(channelId);
        List<Message> allMessagesInChannel = userChannels.stream().map(UserChannel::getMessages).flatMap(List::stream).toList();
        return ResponseEntity.ok(allMessagesInChannel);
    }

    @PostMapping("/users/{userId}/channels/{channelId}/messages")
    public ResponseEntity<Message> addMessage(@RequestBody MessageDto messageDto,
                                              @PathVariable int userId,
                                              @PathVariable int channelId) {
        Optional<UserChannel> userChannel = userChannelService.findUserChannelByUserIdAndChannelId(userId, channelId);

        if(userChannel.isPresent()) {
            Message message = messageDto.toEntity(userChannel.get());
            messageService.addMessage(message);
            userChannel.get().getMessages().add(message);
        }
        return ResponseEntity.created(URI.create("")).build();
    }
}
