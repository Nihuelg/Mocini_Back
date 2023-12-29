package com.example.ProductosMocini.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProductosMocini.models.dtos.ProductsDto;
import com.example.ProductosMocini.models.dtos.ProductsRequest;
import com.example.ProductosMocini.models.entity.Products;
import com.example.ProductosMocini.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProductsService {
    private final ProductsRepository productsRepository;

    public void addProduct(ProductsRequest productRequest){
        Products products = Products.builder()
            .product(productRequest.getProduct())
            .price(productRequest.getPrice())
            .price_porcent(productRequest.getPrice_porcent())
            .build();
        productsRepository.save(products);
    }
    
    public List<ProductsDto> getAllProduct(){
        List<Products> products = productsRepository.findAll();
        return products.stream().map(this::mapProductsDto).toList();
    }

    public ProductsDto mapProductsDto(Products products){
        return ProductsDto.builder()
            .product(products.getProduct())
            .price(products.getPrice())
            .price_porcent(products.getPrice_porcent())
            .build();
    }

    public Products modifProduct(Long id, Products products){
        Products productsFound = productsRepository.findById(id).orElse(null);
        if(productsFound != null){
            productsFound.setProduct(products.getProduct());
            productsFound.setPrice(products.getPrice());
            productsFound.setPrice_porcent(products.getPrice_porcent());
        }
        return this.productsRepository.save(productsFound);
    }

    public void save(Products products){
        productsRepository.save(products);
    }

    public void deleteProducts(Long id){
        this.productsRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return productsRepository.existsById(id);
    }
}
