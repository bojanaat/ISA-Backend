package com.example.backend.service;

import com.example.backend.dto.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> getAllActiveOrders();
}
