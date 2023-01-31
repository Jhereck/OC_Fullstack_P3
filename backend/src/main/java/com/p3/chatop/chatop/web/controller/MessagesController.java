package com.p3.chatop.chatop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.chatop.chatop.web.model.Messages;
import com.p3.chatop.chatop.web.payload.response.MessageResponse;
import com.p3.chatop.chatop.web.repository.MessagesRepository;

@RequestMapping("/api")
@RestController
public class MessagesController {

    @Autowired
    MessagesRepository messagesRepository;

    @PostMapping(value = "/messages")
    public Object addMessage(@RequestBody Messages message) {
        messagesRepository.save(message);
        return new MessageResponse("Message send with success");
    }

}
