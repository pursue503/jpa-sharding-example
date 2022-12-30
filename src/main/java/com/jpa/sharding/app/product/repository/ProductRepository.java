package com.jpa.sharding.app.product.repository;

import com.jpa.sharding.app.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
