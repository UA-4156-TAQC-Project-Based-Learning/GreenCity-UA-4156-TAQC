package com.greencity.api.models.friend;

import lombok.Data;

@Data
public class ResponceGetRecommendedFriends {
    private int id;
    private String name;
    private String email;
    private int rating;
    private int mutualFriends;
    private String profilePicturePath;
    private int chatId;
    private String friendStatus;
    private int requesterId;
    private UserLocationDto userLocationDto;
}
