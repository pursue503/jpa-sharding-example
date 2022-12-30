package com.jpa.sharding.app.product.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductResponseDTO {

    private final Long id;
    private final String productName;

}
