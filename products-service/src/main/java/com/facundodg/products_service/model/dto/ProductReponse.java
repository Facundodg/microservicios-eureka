package com.facundodg.products_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReponse {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private double price;

    private Boolean status;

}
