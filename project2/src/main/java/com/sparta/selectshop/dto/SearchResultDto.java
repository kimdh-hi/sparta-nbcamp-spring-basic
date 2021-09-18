package com.sparta.selectshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResultDto {

    private String title;
    private String image;
    private String url;
    private int lprice;
}
