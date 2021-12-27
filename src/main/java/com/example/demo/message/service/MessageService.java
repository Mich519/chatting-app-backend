package com.example.demo.message.service;

import com.example.demo.message.model.Message;
import com.example.demo.message.repository.MessageRepository;
import com.example.demo.userchannel.model.UserChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> findAllMessagesByUserChannel(UserChannel userChannel) {
        return messageRepository.findAllByUserChannel(userChannel);
    }

    public Optional<Message> findMessageById(Long messageId) {
        return messageRepository.findById(messageId);
    }

    @Transactional
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }


}
