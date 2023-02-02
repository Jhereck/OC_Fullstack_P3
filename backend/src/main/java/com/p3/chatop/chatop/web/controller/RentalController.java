package com.p3.chatop.chatop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p3.chatop.chatop.web.model.Rental;
import com.p3.chatop.chatop.web.payload.request.RentalCreationRequest;
import com.p3.chatop.chatop.web.payload.response.MessageResponse;
import com.p3.chatop.chatop.web.payload.response.RentalsResponse;
import com.p3.chatop.chatop.web.repository.RentalRepository;
import com.p3.chatop.chatop.web.service.RentalCreationService;

@RequestMapping("/api")
@RestController
public class RentalController {

    @Autowired
    RentalRepository rentalsRepository;

    @Autowired
    RentalCreationService rentalCreationService;

    @GetMapping("/rentals")
    public RentalsResponse listRentals() {
        return new RentalsResponse(rentalsRepository.findAll());
    }

    @GetMapping(value = "/rentals/{id}")
    public Rental afficherUnRental(@PathVariable int id) {
        return rentalsRepository.findById(id);
    }

    @PutMapping(value = "/rentals/{id}")
    public MessageResponse updateUnRental(@ModelAttribute Rental rental, @PathVariable int id) {
        Rental goodRental = rentalsRepository.findById(id);
        rental.setOwner_id(goodRental.getOwner_id());
        rental.setPicture(goodRental.getPicture());
        rental.setCreated_at(goodRental.getCreated_at());
        rentalsRepository.save(rental);
        return new MessageResponse("Rental updated !");

    }

    @PostMapping(value = "/rentals")
    public MessageResponse addRental(@RequestHeader("Authorization") String authorization,
            @ModelAttribute RentalCreationRequest rental) {

        rentalsRepository.save(rentalCreationService.handlePicture(authorization, rental));

        return new MessageResponse("Rental created !");

    }

}