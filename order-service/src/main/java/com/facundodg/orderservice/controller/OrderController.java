package com.facundodg.orderservice.controller;

import com.facundodg.orderservice.model.dto.OrderRequest;
import com.facundodg.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){

        this.orderService.preaceOrder(orderRequest);

        return "hola";

    }

}
