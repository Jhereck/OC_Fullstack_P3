package com.p3.chatop.chatop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.chatop.chatop.web.repository.UserRepository;

import org.springframework.web.bind.annotation.RequestBody;

import com.p3.chatop.chatop.web.model.User;

@RequestMapping("/api")
@RestController
public class UsersController {

    @Autowired
    UserRepository usersRepository;

    @GetMapping("/users")
    public List<User> listUsers() {
        return usersRepository.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User afficherUnUser(@PathVariable int id) {
        return usersRepository.findById(id);
    }

    @PostMapping(value = "/auth/register")
    public void addUser(@RequestBody User user) {
        usersRepository.save(user);
    }

}