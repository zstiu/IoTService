package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
