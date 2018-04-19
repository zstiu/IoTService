package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
