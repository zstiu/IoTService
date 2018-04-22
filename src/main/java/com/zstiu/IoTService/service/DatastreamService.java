package com.zstiu.IoTService.service;

import com.zstiu.IoTService.domain.Datastream;

import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 */
public interface DatastreamService {
    public void updateDataStream(Datastream datastream);

    public List<Datastream> getAll();

    public List<Datastream> getByDevice_id(Long device_id);

    public Datastream getByIdAndDevice_id(Long device_id, String id);
}
