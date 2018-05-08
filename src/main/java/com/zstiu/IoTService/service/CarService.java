package com.zstiu.IoTService.service;

import com.zstiu.IoTService.domain.Car;

import java.util.List;

public interface CarService {

    public Car addCar(Car car);

    public Car editCar(Car car);

    public List<Car> getAll();

    public List<Car> getCarByUserId(Long userId);
}
