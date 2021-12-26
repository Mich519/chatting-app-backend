package com.example.demo.channel.model;

import com.example.demo.user.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "channel")
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @CreatedDate
    private Date created;

    @ManyToMany(mappedBy = "channels")
    @Setter
    private List<User> users;
}
