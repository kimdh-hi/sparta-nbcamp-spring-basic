package com.sparta.memo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemoRequestDto {

    private String username;
    private String contents;
}
