package com.example.demo.message.model;

import com.example.demo.userchannel.model.UserChannel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/*TODO: attachments*/

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;

    @ManyToOne
    @JoinColumn(name = "userChannelId")
    @Setter
    @JsonIgnore
    private UserChannel userChannel;
}
