package com.p3.chatop.chatop.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p3.chatop.chatop.web.controller.LoginController;
import com.p3.chatop.chatop.web.model.Rental;
import com.p3.chatop.chatop.web.payload.request.RentalCreationRequest;
import com.p3.chatop.chatop.web.repository.UserRepository;

@Service
public class RentalCreationService {

    @Autowired
    UserRepository usersRepository;

    public Rental handlePicture(String authorization, RentalCreationRequest rental) {

        Rental savedRental = new Rental(rental.getName(), rental.getSurface(), rental.getPrice(),
                rental.getPicture().getOriginalFilename(), rental.getDescription(), LoginController.USER_ID);

        return savedRental;
    }
}
