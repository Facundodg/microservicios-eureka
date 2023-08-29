package com.facundodg.inventoryservice.repositories;

import com.facundodg.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepocitory extends JpaRepository<Inventory,Long> {


    Optional<Inventory> findBySku(String sku);

    List<Inventory> findBySkuIn(List<String> skus);
}
