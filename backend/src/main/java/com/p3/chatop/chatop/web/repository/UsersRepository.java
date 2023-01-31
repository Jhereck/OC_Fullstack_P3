package com.p3.chatop.chatop.web.repository;

import com.p3.chatop.chatop.web.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    List<Users> findAll();

    Users findById(int id);

    Optional<Users> findByEmail(String email);

    Users findUserByEmail(String email);
}
