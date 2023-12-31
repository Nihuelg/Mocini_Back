package com.example.MociniBack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.MociniBack.models.dtos.CategorysDto;
import com.example.MociniBack.models.dtos.CategorysRequest;
import com.example.MociniBack.models.entity.Categorys;
import com.example.MociniBack.repository.CategorysRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategorysService {
    private final CategorysRepository categorysRepository;

    public void addCategory(CategorysRequest categoryRequest){
        Categorys category = Categorys.builder()
        .category(categoryRequest.getCategory())
        .build();
        categorysRepository.save(category);
    }

    public List<CategorysDto> getAllCategory(){
        List<Categorys> category = categorysRepository.findAll();
        return category.stream().map(this::mapCategorysDto).toList();
    }

    public CategorysDto mapCategorysDto(Categorys categorys){

        return CategorysDto.builder()
        .id(categorys.getId())
        .category(categorys.getCategory())
        .build();
    }

     public boolean existsByCategory(String category){
        return categorysRepository.existsByCategory(category);
    }

    public Optional<Categorys> getByCategory(String category){
        return categorysRepository.findByCategory(category);
    }

    public Optional<Categorys> listarID(Long id){
        return categorysRepository.findById(id);
    }

    public Categorys modifCategory(Long id, Categorys category){
        Categorys categoryFound = categorysRepository.findById(id).orElse(null);
        if(categoryFound != null){
           categoryFound.setCategory(category.getCategory());
           
        }
        return this.categorysRepository.save(categoryFound);
    }

    public void save(Categorys category){
        categorysRepository.save(category);
    }

    public void deleteCategorys(Long id){
        this.categorysRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return categorysRepository.existsById(id);
    }

}
