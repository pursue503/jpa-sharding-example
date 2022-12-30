package com.jpa.sharding.app.product.service;

import com.jpa.sharding.app.product.domain.Product;
import com.jpa.sharding.app.product.dto.ProductResponseDTO;
import com.jpa.sharding.app.product.dto.ProductSaveRequestDTO;
import com.jpa.sharding.app.product.repository.ProductRepository;
import com.jpa.sharding.config.ShardTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @ShardTransactional(readOnly = true)
    public ProductResponseDTO findById(Long id, String shardNumber) {
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return new ProductResponseDTO(product.getId(), product.getProductName());
    }

    @ShardTransactional
    public void save(ProductSaveRequestDTO productSaveRequestDTO, String shardNumber) {
        productRepository.save(productSaveRequestDTO.toEntity());
    }
}
