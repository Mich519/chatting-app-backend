package com.example.demo.userchannel.model;

import com.example.demo.channel.model.Channel;
import com.example.demo.user.model.User;
import lombok.*;

import javax.persistence.*;

/**
 * Entity that implements many-to-many relationship between User and Channel
 */
@Entity
@Table(name = "userChannel",
        uniqueConstraints = {@UniqueConstraint( columnNames = {"userId", "channelId"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "channelId")
    private Channel channel;
}
