package com.greencity.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSignIn {

    private String email;
    private String password;
    private String secretKey;

}
