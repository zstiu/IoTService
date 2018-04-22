package com.zstiu.IoTService.repository;

import com.zstiu.IoTService.domain.Datastream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 */
public interface DatastreamRepository extends JpaRepository<Datastream, Long> {

    @Query("from Datastream d where d.device_id=:device_id")
    List<Datastream> findByDevice_id(@Param("device_id") Long device_id);

    @Query("from Datastream d where d.device_id=:device_id and d.id=:id")
    Datastream findByIdAndDevice_id(@Param("device_id") Long device_id, @Param("id") String id);

}
