package com.example.demo.message.repository;

import com.example.demo.message.model.Message;
import com.example.demo.userchannel.model.UserChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserChannel(UserChannel userChannel);
}
