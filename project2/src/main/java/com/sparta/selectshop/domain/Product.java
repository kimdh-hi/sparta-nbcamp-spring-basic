package com.sparta.selectshop.domain;

import com.sparta.selectshop.dto.ProductRequestDto;
import com.sparta.selectshop.dto.SearchResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class Product extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice; // 최저가

    @Column(nullable = false)
    private int myPrice; // 내가 설정한 관심 가격

    public void setMyPrice(int myPrice) {
        this.myPrice = myPrice;
    }

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myPrice = 0;
    }

    public void updateBySearchResultDto(SearchResultDto requestDto) {
        this.lprice = requestDto.getLprice();
    }
}