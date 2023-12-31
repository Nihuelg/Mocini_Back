package com.example.MociniBack.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductsDto {
    private Long id;

    private String product;

    private float price;

    private float price_porcent;
}
