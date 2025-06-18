package com.greencity.api.models.user;

import lombok.Data;

@Data
public class RequestSignIn {

    private String email;
    private String password;
    private String secretKey;

}
