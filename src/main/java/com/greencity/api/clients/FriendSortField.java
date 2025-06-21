package com.greencity.api.clients;

import lombok.Getter;

@Getter
public enum FriendSortField {
    NAME("name");

    private final String field;

    FriendSortField(String field) {
        this.field = field;
    }

}
