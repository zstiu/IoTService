package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Datastream;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/4/22.
 */
public interface DatastreamRepository extends JpaRepository<Datastream, Long> {
}
