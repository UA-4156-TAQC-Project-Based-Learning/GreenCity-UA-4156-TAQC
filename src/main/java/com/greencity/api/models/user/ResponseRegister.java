package com.greencity.api.models.user;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseRegister {
    private int id;
    private String name;
    private String email;
    private Date dateOfRegistration;
    private String userStatus;
    private String role;
    private String languageCode;
}
