package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("from Car c where c.carNumber=:carNumber")
    Car findCarByCarNumber(@Param("carNumber") String carNumber);

//    @Query("from Car c where c.userId=:userId")
    List<Car> findCarByUserId(Long userId);

}
