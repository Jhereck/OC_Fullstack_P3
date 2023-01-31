package com.p3.chatop.chatop.web.payload.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RentalCreationRequest {

    private int id;
    private String name;
    private int surface;
    private int price;
    private MultipartFile picture;
    private String description;
    private int owner_id;

}
