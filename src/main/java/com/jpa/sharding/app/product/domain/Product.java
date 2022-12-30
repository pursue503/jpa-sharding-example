package com.jpa.sharding.app.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    public Product(String productName) {
        this.productName = productName;
    }

    public void productNameUpdate(String shardNumber) {
        productName = shardNumber + "_" + LocalDateTime.now();
    }

}
