package com.p3.chatop.chatop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p3.chatop.chatop.web.model.Messages;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer> {

    Messages findById(int id);

}
