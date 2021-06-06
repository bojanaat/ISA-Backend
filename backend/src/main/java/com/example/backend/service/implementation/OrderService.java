package com.example.backend.service.implementation;

import com.example.backend.dto.response.ComplaintMedicineResponse;
import com.example.backend.dto.response.OrderResponse;
import com.example.backend.model.Complaint;
import com.example.backend.model.Order;
import com.example.backend.repository.IOrderRepository;
import com.example.backend.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository _iOrderRepository;

    private final PharmacyService _pharmacyService;



    public OrderService(IOrderRepository iOrderRepository, PharmacyService pharmacyService) {
        _iOrderRepository = iOrderRepository;
        _pharmacyService = pharmacyService;
    }

    public OrderResponse mapToResponse(Order savedOrder) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setPharmacy(_pharmacyService.mapToResponse(savedOrder.getPharmacy()));
        orderResponse.setId(savedOrder.getId());
        orderResponse.setDeadLine(savedOrder.getDeadLine());
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllActiveOrders() {
        List<Order> orders = _iOrderRepository.findAll();
        List<OrderResponse> finalOrders = new ArrayList<>();
        for(Order o: orders){
            finalOrders.add(mapToResponse(o));
        }
        return finalOrders;
    }
}
