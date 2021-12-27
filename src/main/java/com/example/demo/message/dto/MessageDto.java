package com.example.demo.message.dto;

import com.example.demo.message.model.Message;
import com.example.demo.userchannel.model.UserChannel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class MessageDto {

    private String content;

    public Message toEntity(UserChannel userChannel) {
        return Message.builder()
                .content(content)
                .userChannel(userChannel)
                .build();
    }
}
