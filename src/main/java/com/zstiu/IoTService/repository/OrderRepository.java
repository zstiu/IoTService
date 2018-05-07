package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
