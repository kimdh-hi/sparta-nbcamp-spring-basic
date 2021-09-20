package com.sparta.selectshop.controller.api;

import com.sparta.selectshop.domain.Product;
import com.sparta.selectshop.dto.ProductMyPriceRequestDto;
import com.sparta.selectshop.dto.ProductRequestDto;
import com.sparta.selectshop.dto.SearchResultDto;
import com.sparta.selectshop.repository.ProductRepository;
import com.sparta.selectshop.service.ProductService;
import com.sparta.selectshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    //== 관심상품 전체 조회 ==//
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    //== 관심상품 등록 ==//
    @PostMapping("/products")
    public Product saveMyProduct(@RequestBody ProductRequestDto productRequestDto) {
        System.out.println(productRequestDto);
        Product product = new Product(productRequestDto);

        productRepository.save(product);
        return product;
    }

    @PutMapping("/products/price")
    public int setMyPrice(@RequestParam Long id,@RequestBody ProductMyPriceRequestDto requestDto) {
        productService.update(id, requestDto);

        return requestDto.getMyPrice();
    }
}
