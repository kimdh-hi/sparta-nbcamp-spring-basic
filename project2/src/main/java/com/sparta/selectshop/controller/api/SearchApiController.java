package com.sparta.selectshop.controller.api;

import com.sparta.selectshop.dto.SearchResultDto;
import com.sparta.selectshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchApiController {

    private final NaverShopSearch naverShopSearch;

    //==Naver 쇼핑 검색 API를 통한 검색==//
    @GetMapping("/search")
    public List<SearchResultDto> search(@RequestParam("query") String query) {
        return naverShopSearch.search(query);
    }
}
