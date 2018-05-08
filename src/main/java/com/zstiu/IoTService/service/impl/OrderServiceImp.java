package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.domain.Order;
import com.zstiu.IoTService.repository.OrderRepository;
import com.zstiu.IoTService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
