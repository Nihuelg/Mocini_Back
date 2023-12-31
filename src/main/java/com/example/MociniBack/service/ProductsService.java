package com.example.MociniBack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.MociniBack.models.dtos.ProductsDto;
import com.example.MociniBack.models.dtos.ProductsRequest;
import com.example.MociniBack.models.entity.Products;
import com.example.MociniBack.repository.ProductsRepository;

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

    public boolean existsByProduct(String product){
        return productsRepository.existsByProduct(product);
    }

    public Optional<Products> getByProduct(String product){
        return productsRepository.findByProduct(product);
    }

    public Optional<Products> listarID(Long id){
        return productsRepository.findById(id);
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
