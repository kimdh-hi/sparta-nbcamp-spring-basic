package com.sparta.selectshop.service;

import com.sparta.selectshop.domain.Product;
import com.sparta.selectshop.dto.ProductMyPriceRequestDto;
import com.sparta.selectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long update(Long id, ProductMyPriceRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        product.setMyPrice(requestDto.getMyPrice());

        return product.getId();
    }
}
