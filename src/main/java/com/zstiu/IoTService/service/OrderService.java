package com.zstiu.IoTService.service;

import com.zstiu.IoTService.domain.Order;

import java.util.List;

public interface OrderService {

    public Order addOrder(Order order);

    public List<Order> getAll();

    public List<Order> getCompletedOrder(boolean complete);

    public List<Order> getOrderByIdAndComplete(Long id, boolean complete);

}
