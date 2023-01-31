package com.p3.chatop.chatop.web.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.p3.chatop.chatop.web.model.Rentals;
import com.p3.chatop.chatop.web.payload.request.RentalCreationRequest;
import com.p3.chatop.chatop.web.payload.response.MessageResponse;
import com.p3.chatop.chatop.web.payload.response.RentalsResponse;
import com.p3.chatop.chatop.web.repository.RentalsRepository;
import com.p3.chatop.chatop.web.service.RentalCreationService;

@RequestMapping("/api")
@RestController
public class RentalController {

    @Autowired
    RentalsRepository rentalsRepository;

    @Autowired
    RentalCreationService rentalCreationService;

    @GetMapping("/rentals")
    public RentalsResponse listRentals() {
        return new RentalsResponse(rentalsRepository.findAll());
    }

    @GetMapping(value = "/rentals/{id}")
    public Rentals afficherUnRental(@PathVariable int id) {
        return rentalsRepository.findById(id);
    }

    @PutMapping(value = "/rentals/{id}")
    public MessageResponse updateUnRental(@ModelAttribute Rentals rental, @PathVariable int id) {
        Rentals goodRental = rentalsRepository.findById(id);
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

        /*
         * String fileName = file.getOriginalFilename();
         * System.out.println(rental);
         * System.out.println(fileName);
         * rental.setPictures(fileName);
         * System.out.println(rental);
         * rental.setOwner_id(1);
         * rental.setId(4);
         * System.out.println(rental);
         * try {
         * file.transferTo(new File("C:\\upload\\" + fileName));
         * } catch (Exception e) {
         * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         * }
         * rentalsRepository.save(rental);
         * return ResponseEntity.ok("File uploaded successfully.");
         */
        /* return new MessageResponse("Rental created !"); */
    }

}