package com.jpa.sharding.app.product.controller;


import com.jpa.sharding.app.product.dto.ProductResponseDTO;
import com.jpa.sharding.app.product.dto.ProductSaveRequestDTO;
import com.jpa.sharding.app.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable("id") Long id, @RequestParam("shardNumber") String shardNumber) {
        return new ResponseEntity<>(productService.findById(id, shardNumber), HttpStatus.OK);
    }

    @PostMapping()
    public void save(@RequestBody ProductSaveRequestDTO productSaveRequestDTO) {
        productService.save(productSaveRequestDTO, productSaveRequestDTO.getShardNumber());
    }

}
