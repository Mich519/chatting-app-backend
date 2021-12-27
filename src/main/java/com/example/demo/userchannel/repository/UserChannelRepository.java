package com.example.demo.userchannel.repository;

import com.example.demo.channel.model.Channel;
import com.example.demo.user.model.User;
import com.example.demo.userchannel.model.UserChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {

    Optional<UserChannel> findById(long id);

    List<UserChannel> findAllByChannelId(int channelId);

    List<UserChannel> findAllByUserId(int userId);

    List<UserChannel> findAllByChannel(Channel channel);

    List<UserChannel> findAllByUser(User user);

    Optional<UserChannel> findByUserAndChannel(User user, Channel channel);
}
