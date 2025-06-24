package com.greencity.api.models.user;

import lombok.Data;

@Data
public class ResponseSignInEmployee {
    private int userId;
    private String username;
    private String email;
    private boolean ownRegistrations;
}
