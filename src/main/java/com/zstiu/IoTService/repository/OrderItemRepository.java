package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("from OrderItem o where o.orderId=:orderId")
    List<OrderItem> findAllByOrderId(@Param("orderId") Long orderId);
}
