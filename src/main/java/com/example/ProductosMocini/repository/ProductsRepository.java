package com.example.ProductosMocini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductosMocini.models.entity.Products;

public interface ProductsRepository extends JpaRepository<Products,Long>{

    

    
}
