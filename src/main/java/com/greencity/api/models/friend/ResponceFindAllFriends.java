package com.greencity.api.models.friend;

import lombok.Data;

@Data
public class ResponceFindAllFriends {
    public Integer id;
    public String name;
    public String email;
    public Integer rating;
    public Integer mutualFriends;
    public String profilePicturePath;
    public Integer chatId;
    public String friendStatus;
    public Integer requesterId;
    public UserLocationDto userLocationDto;
}
