package com.p3.chatop.chatop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.p3.chatop.chatop.web.model.Rentals;

import jakarta.transaction.Transactional;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, Integer> {

    Rentals findById(int id);

}