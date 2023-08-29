package com.facundodg.orderservice.repositories;

import com.facundodg.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositorie extends JpaRepository<Order,Long> {

}
