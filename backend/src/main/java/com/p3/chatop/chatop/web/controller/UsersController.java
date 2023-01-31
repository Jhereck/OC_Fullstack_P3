package com.p3.chatop.chatop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.chatop.chatop.web.repository.UsersRepository;

import org.springframework.web.bind.annotation.RequestBody;

import com.p3.chatop.chatop.web.model.Users;

@RequestMapping("/api")
@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Users> listUsers() {
        return usersRepository.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public Users afficherUnUser(@PathVariable int id) {
        return usersRepository.findById(id);
    }

    @PostMapping(value = "/auth/register")
    public void addUser(@RequestBody Users user) {
        usersRepository.save(user);
    }

}