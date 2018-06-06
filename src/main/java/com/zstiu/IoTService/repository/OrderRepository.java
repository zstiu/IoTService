package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    List<Order> findAll();

    @Query("from Order o where o.complete=:complete")
    List<Order> findAllByComplete(@Param("complete") boolean complete);

    @Query("from Order o where o.id=:id and o.complete=:complete")
    List<Order> findAllByIdAndComplete(@Param("id") Long id,@Param("complete") boolean complete);

}
