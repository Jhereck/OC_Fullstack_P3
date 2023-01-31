package com.p3.chatop.chatop.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p3.chatop.chatop.web.controller.LoginController;
import com.p3.chatop.chatop.web.model.Rentals;
import com.p3.chatop.chatop.web.model.Users;
import com.p3.chatop.chatop.web.payload.request.RentalCreationRequest;
import com.p3.chatop.chatop.web.repository.UsersRepository;

import io.jsonwebtoken.Jwts;

@Service
public class RentalCreationService {

    @Autowired
    UsersRepository usersRepository;

    public Rentals handlePicture(String authorization, RentalCreationRequest rental) {

        Rentals savedRental = new Rentals();
        savedRental.setName(rental.getName());
        savedRental.setSurface(rental.getSurface());
        savedRental.setPrice(rental.getPrice());
        savedRental.setPicture(rental.getPicture().getOriginalFilename());
        savedRental.setDescription(rental.getDescription());

        String jwt = authorization.substring(7);

        try {
            // Verify the JWT and extract the user's subject (username)
            String username = Jwts.parser()
                    .setSigningKey(
                            LoginController.KEY)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

            // The JWT is valid, so allow the request

            Users existingUserEmail = usersRepository.findUserByEmail(username);

            savedRental.setOwner_id(existingUserEmail.getId());
        } catch (Exception e) {
            // The JWT is invalid, so return an error
            System.out.println(e);
        }

        return savedRental;
    }
}
