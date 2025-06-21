package com.greencity.api.models.friend;

import lombok.Data;

@Data
public class ResponceGetAllUserFriends {
    private Integer id;
    private String name;
    private String email;
    private Integer rating;
    private Integer mutualFriends;
    private String profilePicturePath;
    private Integer chatId;
    private String friendStatus;
    private Integer requesterId;
    private UserLocationDto userLocationDto;
}
