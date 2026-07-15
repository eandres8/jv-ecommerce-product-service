package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO requestDTO) {
        return productService.createProduct(requestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> listProducts() {
        return productService.getAllsProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO getProducts(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updateProduct(@PathVariable String id, @RequestBody ProductRequestDTO requestDTO) {
        return productService.updateProduct(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
