package com.p3.chatop.chatop.web.payload.response;

import java.util.List;

import com.p3.chatop.chatop.web.model.Rentals;

import lombok.Data;

@Data
public class RentalsResponse {

    private List<Rentals> rentals;

    public RentalsResponse(List rentals) {
        this.rentals = rentals;
    }

}
