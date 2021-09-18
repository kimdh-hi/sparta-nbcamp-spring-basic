package com.sparta.selectshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ProductRequestDto {

    private String title;

    private String link;

    private String image;

    private int lprice;
}
