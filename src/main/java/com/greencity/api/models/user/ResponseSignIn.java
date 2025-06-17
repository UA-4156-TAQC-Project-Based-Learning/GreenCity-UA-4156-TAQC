package com.greencity.api.models.user;

import lombok.Data;

@Data
public class ResponseSignIn {

    private long userId;
    private String name;
    private String accessToken;
    private String refreshToken;
    private boolean ownRegistrations;

}
