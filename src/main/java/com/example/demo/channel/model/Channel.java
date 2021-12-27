package com.example.demo.channel.model;

import com.example.demo.userchannel.model.UserChannel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "channel")
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @CreatedDate
    private Date created;

    @Setter
    @OneToMany(mappedBy = "channel")
    @JsonIgnore
    private List<UserChannel> userChannels;
}
