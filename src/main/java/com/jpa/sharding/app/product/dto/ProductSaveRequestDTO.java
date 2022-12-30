package com.jpa.sharding.app.product.dto;

import com.jpa.sharding.app.product.domain.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductSaveRequestDTO {

    private final String productName;
    private final String shardNumber;

    public Product toEntity() {
        return new Product(productName);
    }

}
