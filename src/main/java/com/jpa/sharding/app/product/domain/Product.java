package com.jpa.sharding.app.product.domain;

import com.jpa.sharding.config.constant.ConstantDB;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(
        catalog = ConstantDB.SHARD_DB_SCHEMA_NAME
)
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
