package com.example.demo.channel.service;

import com.example.demo.channel.model.Channel;
import com.example.demo.channel.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> findAllChannels() {
        return channelRepository.findAll();
    }

    public Optional<Channel> findChannel(int id) {
        return channelRepository.findById(id);
    }

    @Transactional
    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    @Transactional
    public void deleteChannel(Channel channel) {
        channelRepository.delete(channel);
    }
}
