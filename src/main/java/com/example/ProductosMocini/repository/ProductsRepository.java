package com.example.ProductosMocini.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductosMocini.models.entity.Products;



public interface ProductsRepository extends JpaRepository<Products,Long>{
    boolean existsByProduct(String product);
    Optional<Products> findByProduct(String product);
}
