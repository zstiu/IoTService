package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.domain.Car;
import com.zstiu.IoTService.repository.CarRepository;
import com.zstiu.IoTService.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car editCar(Car car) {
        return carRepository.saveAndFlush(car);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getCarByUserId(Long userId) {
        return carRepository.findCarByUserId(userId);
    }
}
