package com.greencity.jdbc.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserEntity {
    public static final String SELECT_ALL = "SELECT * FROM users";
    public static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email = '%s'";

    private Integer id;
    private Timestamp dateOfRegistration;
    private String email;
    private Integer emailNotification;
    private String name;
    private String role;
    private Integer userStatus;
    private String refreshTokenKey;
    private String profilePicture;
    private Double rating;
    private Timestamp lastActivityTime;
    private String firstName;
    private String userCredo;
    private String showLocation;
    private String showEcoPlace;
    private String showToDoList;
    private Long languageId;
    private String uuid;
    private String phoneNumber;
    private Double eventOrganizerRating;
    private Long userLocation;


    public static UserEntity parseRow(List<String> row) {
        UserEntity user = new UserEntity();
        user.setId(Integer.parseInt(row.get(0)));
//        ToDo impl
        return user;
    }

    public static List<UserEntity> parseRows(List<List<String>> rows) {
        List<UserEntity> users = new ArrayList<UserEntity>();
        for (List<String> row : rows) {
            users.add(UserEntity.parseRow(row));
        }
        return users;
    }


}
