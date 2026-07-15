package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.mapper.ProductMapper;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper mapper;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = mapper.toProduct(requestDTO);

        Product newProduct = productRepository.save(product);

        return mapper.toProductResponseDTO(newProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllsProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(mapper::toProductResponseDTO).toList();
    }

    @Override
    public ProductResponseDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado por el id: "+ id)
        );

        return mapper.toProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(String id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado por el id: "+ id)
        );
        mapper.updateProductFromRequest(requestDTO, product);

        Product updatedProduct = productRepository.save(product);
        return mapper.toProductResponseDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado por el id: "+ id);
        }

        productRepository.deleteById(id);
    }
}
