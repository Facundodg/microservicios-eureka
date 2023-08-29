package com.facundodg.inventoryservice.controllers;


import com.facundodg.inventoryservice.model.dto.BaseResponse;
import com.facundodg.inventoryservice.model.dto.OrderItemRequest;
import com.facundodg.inventoryservice.services.InventoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServices inventoryServices;

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String sku) {
        return inventoryServices.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItems) {
        return inventoryServices.areInStock(orderItems);
    }

}
