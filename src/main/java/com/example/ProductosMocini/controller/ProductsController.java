package com.example.ProductosMocini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductosMocini.models.dtos.ProductsDto;
import com.example.ProductosMocini.models.dtos.ProductsRequest;
import com.example.ProductosMocini.models.entity.Products;
import com.example.ProductosMocini.service.ProductsService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductsController {
     
@Autowired
private final ProductsService productsService;



    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void newProduct(@RequestBody ProductsRequest productsRequest){
    this.productsService.addProduct(productsRequest);
    }

    @GetMapping("/all") 
    public List<ProductsDto> getAllProduct(){
        return this.productsService.getAllProduct();
    }  
    
    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Products> findProductById(@PathVariable Long id){
        Products products = productsService.listarID(id).get();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ProductsDto productsDto){
        if(!productsService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(productsService.existsByProduct(productsDto.getProduct()) && productsService.getByProduct(productsDto.getProduct()).get().getId() != id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productsDto.getProduct()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(productsDto.getPrice()>= 0.1 || productsDto.getPrice()<0 )
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        Products products = productsService.listarID(id).get();
        products.setProduct(productsDto.getProduct());
        products.setPrice(productsDto.getPrice());
        products.setPrice_porcent(productsDto.getPrice_porcent());
        productsService.save(products);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!productsService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        productsService.deleteProducts(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }   
}
