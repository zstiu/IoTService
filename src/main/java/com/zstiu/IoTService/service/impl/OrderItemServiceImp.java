package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.domain.OrderItem;
import com.zstiu.IoTService.repository.OrderItemRepository;
import com.zstiu.IoTService.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImp implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem addOrderDeviceCar(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
