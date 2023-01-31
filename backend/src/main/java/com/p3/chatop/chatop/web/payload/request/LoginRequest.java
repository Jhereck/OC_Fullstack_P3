package com.p3.chatop.chatop.web.payload.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
