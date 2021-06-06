package com.example.backend.controller;

import com.example.backend.dto.response.OrderResponse;
import com.example.backend.service.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService _iOrderService;

    public OrderController(IOrderService iOrderService) {
        _iOrderService = iOrderService;
    }

    @GetMapping()
    public List<OrderResponse> getAllActiveOrders(){
        return _iOrderService.getAllActiveOrders();
    }
}
