package com.zstiu.IoTService.service;

import com.zstiu.IoTService.domain.Device;

import java.util.ArrayList;
import java.util.List;

public interface DeviceService {

    public void updateDevice(Device device);

    public List<Device> getAll();

    public Device getById(Long id);

}
