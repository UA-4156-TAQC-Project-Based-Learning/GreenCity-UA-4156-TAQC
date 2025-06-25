package com.greencity.api.models.econews;

import lombok.Data;

import java.util.List;

@Data
public class ResponseEcoNewsList {
    private List<ResponseEcoNews> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;
    private int number;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean first;
    private boolean last;
}
