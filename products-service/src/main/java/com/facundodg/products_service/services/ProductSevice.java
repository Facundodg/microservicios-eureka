package com.facundodg.products_service.services;

import com.facundodg.products_service.model.dto.ProductReponse;
import com.facundodg.products_service.model.dto.ProductRequest;
import com.facundodg.products_service.model.entities.Product;
import com.facundodg.products_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSevice {

    private final ProductRepository productRepository;

    public void addProduct(ProductRequest productRequest){

        var product = Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescripcion())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();

        productRepository.save(product);

        log.info("product added: {}", product);

    }

    public List<ProductReponse> getAllProducts(){

        var products = productRepository.findAll();

        return products.stream().map(this::mapToProductoResponse).toList();

    }

    private ProductReponse mapToProductoResponse(Product product){

        return ProductReponse.builder()
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();

    }


}
