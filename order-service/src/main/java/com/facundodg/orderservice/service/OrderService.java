package com.facundodg.orderservice.service;


import com.facundodg.orderservice.config.WebClientConfig;
import com.facundodg.orderservice.model.Order;
import com.facundodg.orderservice.model.OrderItems;
import com.facundodg.orderservice.model.dto.BaseResponse;
import com.facundodg.orderservice.model.dto.OrderItemRequest;
import com.facundodg.orderservice.model.dto.OrderRequest;
import com.facundodg.orderservice.repositories.OrderRepositorie;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepositorie orderRepositorie;
    private final WebClient.Builder webClientBuilder;

    public void preaceOrder(OrderRequest orderRequest){

        //chack inventario aqui

        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if(result != null && !result.hasErrors()) {

            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequesToOrderItem(orderItemRequest, order))
                    .toList());
            this.orderRepositorie.save(order);

        }else{

            throw new IllegalArgumentException("SE PRODUJO UN ERROR");

        }

    }

    private OrderItems mapOrderItemRequesToOrderItem(OrderItemRequest orderItemRequest, Order order) {

        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }

}
