package com.example.backend.service.implementation;

import com.example.backend.dto.request.CreateOfferRequest;
import com.example.backend.dto.response.OfferResponse;
import com.example.backend.model.*;
import com.example.backend.repository.IOfferRepository;
import com.example.backend.repository.IOrderRepository;
import com.example.backend.repository.ISupplierRepository;
import com.example.backend.service.IOfferService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OfferService implements IOfferService {

    private final ISupplierRepository _iSupplierRepository;
    private final IOrderRepository _iOrderRepository;
    private final IOfferRepository _iOfferRepository;

    private final OrderService _orderService;
    private final SupplierService _supplierService;

    public OfferService(ISupplierRepository iSupplierRepository, IOrderRepository iOrderRepository, IOfferRepository iOfferRepository, OrderService orderService, SupplierService supplierService) {
        _iSupplierRepository = iSupplierRepository;
        _iOrderRepository = iOrderRepository;
        _iOfferRepository = iOfferRepository;
        _orderService = orderService;
        _supplierService = supplierService;
    }

    @Override
    public OfferResponse createOffer(CreateOfferRequest request) {
        Supplier supplier = _iSupplierRepository.findOneById(request.getSupplierId());
        List<MedicineSupplier> supplierMedicaments = supplier.getMedicineSuppliers();
        Order order = _iOrderRepository.findOneById(request.getOrderId());
        List<MedicineOrder> orderMedicaments = order.getMedicineOrders();

        Offer offer = new Offer();
        LocalDate deadLine = LocalDate.parse(request.getDeadLine());

        for(MedicineOrder orderMedicament: orderMedicaments){
            int counter = 0;
            for(MedicineSupplier supplierMedicament: supplierMedicaments){
                if(orderMedicament.getMedicine().getId() == supplierMedicament.getMedicine().getId()){
                    if(orderMedicament.getQuantity() > supplierMedicament.getQuantity()){
                        return null;
                    }
                }else{
                    counter++;
                }

                if(counter == supplierMedicaments.size()){
                    return null;
                }
            }
        }

        offer.setDeadLine(deadLine);
        offer.setTotalPrice(request.getTotalPrice());
        offer.setOrder(order);
        offer.setSupplier(supplier);
        Offer savedOffer = _iOfferRepository.save(offer);

        return mapToResponse(savedOffer);
    }

    private OfferResponse mapToResponse(Offer savedOffer) {
        OfferResponse offerResponse = new OfferResponse();
        offerResponse.setId(savedOffer.getId());
        offerResponse.setDeadLine(savedOffer.getDeadLine());
        offerResponse.setOrderResponse(_orderService.mapToResponse(savedOffer.getOrder()));
        offerResponse.setSupplier(_supplierService.mapToResponse(savedOffer.getSupplier()) );
        offerResponse.setTotalPrice(savedOffer.getTotalPrice());
        return offerResponse;
    }
}
