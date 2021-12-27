package com.example.demo.userchannel.dto;

import com.example.demo.channel.model.Channel;
import com.example.demo.user.model.User;
import com.example.demo.userchannel.model.UserChannel;
import lombok.Getter;

@Getter
public class UserChannelDto {

    private int userId;

    private int channelId;

    /**
     * Convert UserChannel DTO to JPA entity
     *
     * @param user user entity that is a part of UserChannel relation
     * @param channel channel entity that is a part of UserChannel relation
     * @return UserChannel JPA entity
     */
    public UserChannel toEntity(User user, Channel channel) {
            return UserChannel.builder()
                    .channel(channel)
                    .user(user)
                    .build();
    }
}

