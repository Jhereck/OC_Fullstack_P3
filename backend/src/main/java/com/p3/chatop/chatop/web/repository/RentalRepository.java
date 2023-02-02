package com.p3.chatop.chatop.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.p3.chatop.chatop.web.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    Rental findById(int id);

}