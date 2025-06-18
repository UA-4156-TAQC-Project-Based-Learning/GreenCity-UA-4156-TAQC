package com.greencity.api.models.econews;

import lombok.Data;

import java.util.List;

@Data
public class RequestEcoNews {

    private String title;
    private String text;
    private List<String> tags;
    private String source;
    private String shortInfo;
}
