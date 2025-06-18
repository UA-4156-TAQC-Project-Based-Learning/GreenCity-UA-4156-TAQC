package com.greencity.api.models.user;

import lombok.Data;

@Data
public class ResponseSignIn {
    private int userId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private boolean ownRegistrations;

}
