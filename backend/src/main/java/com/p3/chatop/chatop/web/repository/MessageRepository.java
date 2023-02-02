package com.p3.chatop.chatop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p3.chatop.chatop.web.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    Message findById(int id);

}
