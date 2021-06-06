package com.example.backend.service.implementation;

import com.example.backend.dto.response.OrderResponse;
import com.example.backend.model.Order;
import com.example.backend.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    private final PharmacyService _pharmacyService;

    public OrderService(PharmacyService pharmacyService) {
        _pharmacyService = pharmacyService;
    }

    public OrderResponse mapToResponse(Order savedOrder) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setPharmacy(_pharmacyService.mapToResponse(savedOrder.getPharmacy()));
        orderResponse.setId(savedOrder.getId());
        orderResponse.setDeadLine(savedOrder.getDeadLine());
        return orderResponse;
    }
}
