package com.zstiu.IoTService.service.impl;

import com.zstiu.IoTService.domain.Device;
import com.zstiu.IoTService.repository.DeviceRepository;
import com.zstiu.IoTService.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService {

    private static final Logger log = LoggerFactory.getLogger(ManagerServiceImp.class);

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public void updateDevice(Device device) {
        deviceRepository.saveAndFlush(device);
    }

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device getById(Long id) {
        return deviceRepository.getOne(id);
    }
}
