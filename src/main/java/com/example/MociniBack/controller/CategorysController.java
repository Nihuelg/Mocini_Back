package com.example.MociniBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.MociniBack.models.dtos.CategorysDto;
import com.example.MociniBack.models.dtos.CategorysRequest;
import com.example.MociniBack.models.entity.Categorys;
import com.example.MociniBack.service.CategorysService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Categoria")
@CrossOrigin(origins = "http://localhost:4200")

public class CategorysController {
    
@Autowired
private final CategorysService categorysService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void newCategory(@RequestBody CategorysRequest categorysRequest){
        this.categorysService.addCategory(categorysRequest);
    }

    @GetMapping("/all")
    public List<CategorysDto> getAllCategory(){
        return this.categorysService.getAllCategory();
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categorys> findCategoryById(@PathVariable Long id){
        Categorys categorys = categorysService.listarID(id).get();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody CategorysDto categorysDto){
        if(!categorysService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(categorysService.existsByCategory(categorysDto.getCategory()) && categorysService.getByCategory(categorysDto.getCategory()).get().getId() != id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(categorysDto.getCategory()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Categorys categorys = categorysService.listarID(id).get();
        categorys.setCategory(categorysDto.getCategory());
        categorysService.save(categorys);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!categorysService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        categorysService.deleteCategorys(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }   
}
