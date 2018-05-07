package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
