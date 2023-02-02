package com.p3.chatop.chatop.web.repository;

import com.p3.chatop.chatop.web.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    User findById(int id);

    Optional<User> findByEmail(String email);

    User findUserByEmail(String email);
}
