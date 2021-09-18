package com.sparta.selectshop.utils;

import com.sparta.selectshop.dto.SearchResultDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:key.properties")
public class NaverShopSearch {

    @Value("${key.client}")
    private String client;

    @Value("${key.secret}")
    private String secret;


    public List<SearchResultDto> search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", client);
        headers.add("X-Naver-Client-Secret", secret);
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query,
                HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();

        int status = httpStatus.value();
        String response = responseEntity.getBody();

        // String -> Json (JSON in Java)
        JSONObject jsonObject = new JSONObject(response);
        // Json 내 array
        JSONArray items = jsonObject.getJSONArray("items");

        List<SearchResultDto> searchResultDtoList = new ArrayList<>();

        // Json 내 array 안의 Json
        for (int i=0; i<items.length();i++) {
            String title = items.getJSONObject(i).getString("title");
            String image = items.getJSONObject(i).getString("image");
            String link = items.getJSONObject(i).getString("link");
            int lprice = items.getJSONObject(i).getInt("lprice");

            searchResultDtoList.add(new SearchResultDto(title, image, link, lprice));
        }

        return searchResultDtoList;
    }
}
