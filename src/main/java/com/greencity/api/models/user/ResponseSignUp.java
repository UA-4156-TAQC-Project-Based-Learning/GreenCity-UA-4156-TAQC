package com.greencity.api.models.user;

import lombok.Data;

@Data
public class ResponseSignUp {
    public int userId;
    public String username;
    public String email;
    public boolean ownRegistrations;



}
