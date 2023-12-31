package com.example.MociniBack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MociniBack.models.entity.Categorys;

public interface CategorysRepository extends JpaRepository<Categorys, Long> {
    
     boolean existsByCategory(String category);
    Optional<Categorys> findByCategory(String category);

}
