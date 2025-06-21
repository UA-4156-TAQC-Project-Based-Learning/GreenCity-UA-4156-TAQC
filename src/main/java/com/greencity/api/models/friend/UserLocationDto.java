package com.greencity.api.models.friend;

import lombok.Data;

@Data
public class UserLocationDto {
    private Integer id;
    private String cityEn;
    private String cityUk;
    private String regionEn;
    private String regionUk;
    private String countryEn;
    private String countryUk;
    private Integer latitude;
    private Integer longitude;
}
