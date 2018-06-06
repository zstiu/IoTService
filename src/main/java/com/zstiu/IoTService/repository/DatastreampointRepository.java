package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Datastreampoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DatastreampointRepository extends JpaRepository<Datastreampoint, Long> {

    @Query("from Datastreampoint d where d.device_id=:device_id order by device_id")
    List<Datastreampoint> findAllDatapoint(@Param("device_id") Long device_id);

}
