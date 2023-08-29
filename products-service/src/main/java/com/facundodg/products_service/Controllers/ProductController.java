package com.facundodg.products_service.Controllers;

import com.facundodg.products_service.model.dto.ProductReponse;
import com.facundodg.products_service.model.dto.ProductRequest;
import com.facundodg.products_service.services.ProductSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(name = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductSevice productService;

    @PostMapping("/agregar")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductReponse> getAllProducts(){

        return this.productService.getAllProducts();

    }

}
