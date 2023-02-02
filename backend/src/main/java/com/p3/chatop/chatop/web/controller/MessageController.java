package com.p3.chatop.chatop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.chatop.chatop.web.model.Message;
import com.p3.chatop.chatop.web.payload.response.MessageResponse;
import com.p3.chatop.chatop.web.repository.MessageRepository;

@RequestMapping("/api")
@RestController
public class MessageController {

    @Autowired
    MessageRepository messagesRepository;

    @PostMapping(value = "/messages")
    public Object addMessage(@RequestBody Message message) {
        messagesRepository.save(message);
        return new MessageResponse("Message send with success");
    }

}
