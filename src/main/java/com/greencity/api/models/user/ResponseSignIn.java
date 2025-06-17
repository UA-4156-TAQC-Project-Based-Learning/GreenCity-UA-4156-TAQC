package com.greencity.api.models.user;

import lombok.Data;

@Data
public class ResponseSignIn {

    private ResponseAuthor author;
    private String accessToken;
    private String refreshToken;
    private boolean ownRegistrations;

}
