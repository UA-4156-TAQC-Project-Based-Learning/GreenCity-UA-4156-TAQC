package com.greencity.api.models.econews;

import lombok.Data;

import java.util.List;

@Data
public class RequestUpdateNews {
    private Long id;
    private String title;
    private String content;
    private List<String> tags;
    private String source;
    private String shortInfo;
}
