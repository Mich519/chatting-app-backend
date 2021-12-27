package com.example.demo.userchannel.service;

import com.example.demo.channel.model.Channel;
import com.example.demo.user.model.User;
import com.example.demo.userchannel.model.UserChannel;
import com.example.demo.userchannel.repository.UserChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserChannelService {

    private final UserChannelRepository userChannelRepository;

    @Autowired
    public UserChannelService(UserChannelRepository userChannelRepository) {
        this.userChannelRepository = userChannelRepository;
    }

    public Optional<UserChannel> findUserChannelById(long id) {
        return userChannelRepository.findById(id);
    }

    public List<UserChannel> findAllUserChannel() {
        return userChannelRepository.findAll();
    }

    public List<UserChannel> findAllUserChannelByChannelId(int channelId) {
        return userChannelRepository.findAllByChannelId(channelId);
    }

    public List<UserChannel> findAllUserChannelByUserId(int userId) {
        return userChannelRepository.findAllByUserId(userId);
    }

    public Optional<UserChannel> findUserChannelByUserAndChannel(User user, Channel channel) {
        return userChannelRepository.findByUserAndChannel(user, channel);
    }

    public UserChannel addUserChannel(UserChannel userChannel) {
        return userChannelRepository.save(userChannel);
    }
}
