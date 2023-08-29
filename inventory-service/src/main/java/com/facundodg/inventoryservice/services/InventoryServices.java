package com.facundodg.inventoryservice.services;

import com.facundodg.inventoryservice.model.Inventory;
import com.facundodg.inventoryservice.model.dto.BaseResponse;
import com.facundodg.inventoryservice.model.dto.OrderItemRequest;
import com.facundodg.inventoryservice.repositories.InventoryRepocitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServices {

    private final InventoryRepocitory inventoryRepocitory;

    public boolean isInStock(String sku) {

        var inventory = inventoryRepocitory.findBySku(sku);

        return inventory.filter(value -> value.getQuantity() > 0).isPresent();

    }

    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {

        var errorList = new ArrayList<String>();

        List<String> skus = orderItems.stream().map(OrderItemRequest:: getSku).toList();
        List<Inventory> inventoryList = inventoryRepocitory.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();
            if (inventory.isEmpty()) {
                errorList.add("Product with sku " + orderItem.getSku() + " does not exist");
            } else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                errorList.add("Product with sku " + orderItem.getSku() + " has insufficient quantity");
            }
        });

        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);

    }
    }




